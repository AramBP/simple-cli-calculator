package Calculator.Sorter;
import java.util.ArrayList;
import java.util.List;

import Calculator.Parser.*;

public class Sorter {
    
    public List<TokenValue> sort(List<TokenValue> input){
        // this function sorts the list of parsed tokens using the shunting yard algorithm
        // https://en.wikipedia.org/wiki/Shunting_yard_algorithm
        List<TokenValue> sortedStack = new ArrayList<>();
        List<TokenValue> operatorStack = new ArrayList<>();

        for (int i = 0; i < input.size(); i++){
            TokenValue token = input.get(i);
            if (token instanceof Digit){
                sortedStack.add(token);
            } else {
                if(operatorStack.size() == 0){
                    operatorStack.add(token);
                } else {
                    TokenValue topOperator = operatorStack.get(0);
                    if (!(topOperator instanceof LeftBrace || topOperator instanceof RightBrace)){
                        if (operatorPrecedence(topOperator) > operatorPrecedence(token) 
                        || operatorPrecedence(token) == operatorPrecedence(topOperator)){
                            operatorStack.remove(topOperator);
                            sortedStack.add(topOperator);
                        }
                        operatorStack.add(token);
                    } else if(topOperator instanceof LeftBrace){
                        operatorStack.addFirst(token);
                    }
                }
            }
        }
        return sortedStack;
    }

    public int operatorPrecedence(TokenValue token){
        // * and / are precedence 1
        // + and - are precedence 0
        int precedence = 0;

        if (token instanceof Multiplication || token instanceof Division){
            precedence = 1;
        }

        return precedence;
    }
}
