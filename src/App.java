import Calculator.Parser.Parser;
import Calculator.Parser.Token;
import Calculator.Planter.Planter;
import Calculator.Sorter.Sorter;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner promptScanner = new Scanner(System.in);
        while (true){
            Parser parser = new Parser();
            Sorter sorter = new Sorter();
            Planter planter = new Planter();

            String prompt = "";
            List<Token> parsedInput= new ArrayList<>();
            List<Token> postfixSortedOutput = new ArrayList<>();
            System.out.print("(P)> ");

            try{
                prompt = promptScanner.nextLine();
                if (prompt.equalsIgnoreCase("exit")){
                    break;
                }
                parsedInput = parser.parse(prompt);
                postfixSortedOutput = sorter.sort(parsedInput);
                
                double result = planter.Plant(postfixSortedOutput);
                System.out.println(result);
                continue;
            } catch (Exception e){
                System.out.println("(~)> " + e.getMessage());
                System.out.println("(~)> prompt string value: " + prompt);
                System.out.println("(~)> parsed: " + parsedInput.get(1));
                System.out.println("(~)> RNP sorted: " + postfixSortedOutput);
                continue;
            }

        }
        promptScanner.close();
    }
}
