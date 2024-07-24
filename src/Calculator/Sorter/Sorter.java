package Calculator.Sorter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Calculator.Parser.*;
import Calculator.Parser.Token.OperatorType;
import Calculator.Parser.Token.TokenType;

public class Sorter {
// use this website to check the output of this function
// https://paodayag.dev/reverse-polish-notation-js-parser/converter.html

    public List<Token> sort(List<Token> input) throws InvalidOperatorStack, UnrecognizedTokenException{
        // this function sorts the list of parsed tokens using the shunting yard algorithm
        // https://en.wikipedia.org/wiki/Shunting_yard_algorithm
        List<Token> postfixQueue = new ArrayList<>();
        List<Token> operatorStack = new ArrayList<>();

        for (int i = 0; i < input.size(); i++){
            Token token = input.get(i);
            if (token.tokenType == TokenType.DIGIT){
                postfixQueue.addLast(token);
            } else if (token.tokenType == TokenType.FUNCTION){
                operatorStack.addLast(token);
            }
            else if (token.tokenType == TokenType.OPERATOR){

                if(token.operatorType == OperatorType.UNARY_POST){
                    postfixQueue.addLast(token);
                } else if (token.operatorType == OperatorType.UNARY_PRE){
                    operatorStack.addLast(token);
                } else if (token.operatorType == OperatorType.BIN_LEFT){
                    while (operatorStack.size() > 0 
                    && operatorStack.getLast().tokenType == TokenType.OPERATOR
                    && operatorStack.getLast().precedence >= token.precedence){
                        postfixQueue.addLast(operatorStack.removeLast());
                    }
                    operatorStack.addLast(token);
                } else if (token.operatorType == OperatorType.BIN_RIGHT){
                    while (operatorStack.size() > 0 
                    && operatorStack.getLast().tokenType == TokenType.OPERATOR
                    && operatorStack.getLast().precedence > token.precedence){
                        postfixQueue.addLast(operatorStack.removeLast());
                    }
                    operatorStack.addLast(token);
                }

            } else if (token.tokenType == TokenType.LEFT_BRACE){
                operatorStack.addLast(token);
            } else if (token.tokenType == TokenType.RIGHT_BRACE){
                try {
                    while(operatorStack.getLast().tokenType != TokenType.LEFT_BRACE){
                        postfixQueue.addLast(operatorStack.removeLast());
                    }
                } catch (NoSuchElementException e) {
                    postfixQueue.clear();
                    throw new NoSuchElementException("Mismatched brackets on shunting stack");
                }
                // there is a left brace on the top of the operator stack
                operatorStack.removeLast();
                if (operatorStack.getLast().tokenType == TokenType.FUNCTION){
                    postfixQueue.addLast(operatorStack.removeLast());
                }
            } else {
                throw new UnrecognizedTokenException(token.toString());
            }
        }
        while (operatorStack.size() > 0){
            if (operatorStack.getLast().tokenType != TokenType.OPERATOR && operatorStack.getLast().tokenType != TokenType.FUNCTION){
                postfixQueue.clear();
                throw new InvalidOperatorStack("");
            } else{
                postfixQueue.addLast(operatorStack.removeLast());
            }
        }
        return postfixQueue;
    }
}
