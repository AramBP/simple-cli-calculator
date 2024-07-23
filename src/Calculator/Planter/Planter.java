package Calculator.Planter;

import Calculator.Parser.Token;
import Calculator.Parser.Token.TokenType;

import java.util.List;
import java.util.ArrayList;

public class Planter {
    public float Plant(List<Token> sortedTokens) throws UnrecognizedExpressionException{
        List<Float> digits = new ArrayList<>();

       for(int i = 0; i < sortedTokens.size(); i++){
            Token token = sortedTokens.get(i);
            if (token.tokenType == TokenType.DIGIT){
                digits.add(token.value);
            } else if (digits.size() >= 2) {
                float rightOperand = digits.removeLast();
                float leftOperand = digits.removeLast();
                switch (token.toString()) {
                    case "+":
                        digits.addLast(new SumExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "/":
                        digits.addLast(new DivisionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "*":
                        digits.addLast(new MultiplicationExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "-":
                        digits.addLast(new SubtractionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    default:
                        throw new UnrecognizedExpressionException(token.toString());
                }
            } else {
                throw new UnrecognizedExpressionException("not enough operands");
            }
        }
        float answer = digits.removeLast();
        if (digits.size() != 0){
            throw new UnrecognizedExpressionException("not enough operators");
        }
        return answer;
    }
}
