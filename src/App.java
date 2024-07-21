import Calculator.Tokenizer.*;
import Calculator.Parser.*;

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
                List<Token> output = new ArrayList<Token>();
                output = tokenizer.splitString(prompt);

                Parser parser = new Parser();
                List<TokenValue> parsedOutput = new ArrayList<TokenValue>();
                parsedOutput = parser.parse(output);
                System.out.print("(~)> ");
                for (int i = 0; i < parsedOutput.size(); i++){
                    System.out.print(parsedOutput.get(i) + " ");
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
