import Calculator.Tokenizer.*;
import Calculator.Parser.*;
import Calculator.Sorter.*;
import Calculator.Planter.Planter;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner promptScanner = new Scanner(System.in);
        while (true){
            System.out.print("(P)> ");
            List<Token> output = new ArrayList<>();
            List<TokenValue> parsedOutput = new ArrayList<>();
            List<TokenValue> postfixSortedOutput = new ArrayList<>();

            try{
                String prompt = promptScanner.nextLine();
                if (prompt.equalsIgnoreCase("exit")){
                    break;
                }

                // validate expression
                Tokenizer tokenizer = new Tokenizer();
                output = tokenizer.splitString(prompt);

                Parser parser = new Parser();
                parsedOutput = parser.parse(output);

                Sorter sorter = new Sorter();
                postfixSortedOutput = sorter.sort(parsedOutput);
                
                Planter planter = new Planter();
                float result = planter.Plant(postfixSortedOutput);
                
                System.out.println("(~)> " + result);
            } catch (Exception e){
                System.out.println("(~)> " + e.getMessage());
                // -------------DEBUG-------------
                System.out.print("(~)> Parsed output: ");
                for (int i = 0; i < parsedOutput.size(); i++){
                    if (parsedOutput.get(i) instanceof Digit){
                        System.out.print(parsedOutput.get(i).ParentToken.value + " ");
                    } else{
                        System.out.print(parsedOutput.get(i).getClass().getSimpleName() + " ");
                    }
                }
                System.out.print("\n");
                
                // https://paodayag.dev/reverse-polish-notation-js-parser/converter.html
                // check if sorter is giving correct output
                System.out.print("(~)> In RPN: ");
                for (int i = 0; i < postfixSortedOutput.size(); i++){
                    if (postfixSortedOutput.get(i) instanceof Digit){
                        System.out.print(postfixSortedOutput.get(i).ParentToken.value + " ");
                    } else{
                        System.out.print(postfixSortedOutput.get(i).getClass().getSimpleName() + " ");
                    }
                }
                System.out.print("\n");
                // -----------------------------            
                continue;
            }

        }
        promptScanner.close();
    }
}
