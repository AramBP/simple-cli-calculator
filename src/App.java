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
            List<Token> parsedInput= new ArrayList<>();
            List<Token> postfixSortedOutput = new ArrayList<>();

            try{
                String prompt = promptScanner.nextLine();
                if (prompt.equalsIgnoreCase("exit")){
                    break;
                }

                Parser parser = new Parser();
                parsedInput = parser.parse(prompt);

                System.out.println(parser.parsedInputToString(parsedInput));
                
                continue;
            } catch (Exception e){
                System.out.println("(~)> " + e.getMessage());
                // https://paodayag.dev/reverse-polish-notation-js-parser/converter.html
                // check if sorter is giving correct output        
                continue;
            }

        }
        promptScanner.close();
    }
}
