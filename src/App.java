import Calculator.Tokenizer.*;
import Calculator.Parser.*;
import Calculator.Sorter.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner promptScanner = new Scanner(System.in);
        while (true){
            System.out.print("(P)> ");

            try{
                String prompt = promptScanner.nextLine();
                
                if (prompt.equalsIgnoreCase("exit")){
                    break;
                }
                // validate expression

                Tokenizer tokenizer = new Tokenizer();
                List<Token> output = new ArrayList<>();
                output = tokenizer.splitString(prompt);

                Parser parser = new Parser();
                List<TokenValue> parsedOutput = new ArrayList<>();
                parsedOutput = parser.parse(output);

                Sorter sorter = new Sorter();
                List<TokenValue> postfixSortedOutput = new ArrayList<>();
                postfixSortedOutput = sorter.sort(parsedOutput);

                System.out.print("(~)> ");
                for (int i = 0; i < parsedOutput.size(); i++){
                    if (parsedOutput.get(i) instanceof Digit){
                        System.out.print(parsedOutput.get(i).ParentToken.value + " ");
                    } else{
                        System.out.print(parsedOutput.get(i).getClass().getSimpleName() + " ");
                    }
                }
                System.out.print("\n");

                System.out.print("(~)> ");
                for (int i = 0; i < postfixSortedOutput.size(); i++){
                    if (postfixSortedOutput.get(i) instanceof Digit){
                        System.out.print(postfixSortedOutput.get(i).ParentToken.value + " ");
                    } else{
                        System.out.print(postfixSortedOutput.get(i).getClass().getSimpleName() + " ");
                    }
                }
                System.out.print("\n");

            } catch (Exception e){
                System.out.println("(~)> " + e.getMessage());
                continue;
            }

        }
        promptScanner.close();
    }
}
