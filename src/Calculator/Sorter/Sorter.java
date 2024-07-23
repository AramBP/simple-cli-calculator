package Calculator.Sorter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Calculator.Parser.*;

public class Sorter {
    
    public List<TokenValue> sort(List<TokenValue> input) throws InvalidOperatorStack{
        // this function sorts the list of parsed tokens using the shunting yard algorithm
        // https://en.wikipedia.org/wiki/Shunting_yard_algorithm
        List<TokenValue> postfixQueue = new ArrayList<>();
        List<TokenValue> operatorStack = new ArrayList<>();

        for (int i = 0; i < input.size(); i++){
            TokenValue token = input.get(i);
            if (token instanceof Digit){
                postfixQueue.addLast(token);
            } else if (isOperator(token)){
                // assumes that the operator is binary left associative 
                while (operatorStack.size() > 0 
                && operatorPrecedence(operatorStack.getLast()) > operatorPrecedence(token)){
                    postfixQueue.addLast(operatorStack.removeLast());
                }
                operatorStack.addLast(token);
            } else if (token instanceof LeftBrace){
                operatorStack.addLast(token);
            } else if (token instanceof RightBrace){
                try {
                    while(!(operatorStack.getLast() instanceof LeftBrace)){
                        postfixQueue.addLast(operatorStack.removeLast());
                    }
                } catch (NoSuchElementException e) {
                    postfixQueue.clear();
                    throw new NoSuchElementException("Mismatched brackets on shunting stack");
                }
                operatorStack.removeLast();
            }
        }
        while (operatorStack.size() > 0){
            if (!isOperator(operatorStack.getLast())){
                postfixQueue.clear();
                throw new InvalidOperatorStack("");
            } else{
                postfixQueue.addLast(operatorStack.removeLast());
            }
        }
        return postfixQueue;
    }

    private Boolean isOperator(TokenValue token){
        if (token instanceof Multiplication || token instanceof Division || token instanceof Sum || token instanceof Subtraction){
            return true;
        } else {
            return false;
        }
    }

    private int operatorPrecedence(TokenValue token){
        // * and / are precedence 1
        // + and - are precedence 0
        int precedence = 0;

        if (token instanceof Multiplication || token instanceof Division){
            precedence = 1;
        }

        return precedence;
    }
}
