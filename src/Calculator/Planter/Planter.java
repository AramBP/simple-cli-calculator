package Calculator.Planter;

import Calculator.Parser.*;
import java.util.List;
import java.util.ArrayList;

public class Planter {
    public float Plant(List<TokenValue> sortedTokens) throws UnrecognizedExpressionException{
        List<Float> digits = new ArrayList<>();

        while(sortedTokens.size() > 0){
            TokenValue token = sortedTokens.removeFirst();
            if (token instanceof Digit){
                Digit digit = (Digit) token;
                digits.add(digit.value);
            } else if (digits.size() >= 2) {
                float leftOperand = digits.removeLast();
                float rightOperand = digits.removeLast();
                switch (token.getClass().getSimpleName()) {
                    case "Sum":
                        digits.addLast(new SumExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "Division":
                        digits.addLast(new DivisionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "Multiplication":
                        digits.addLast(new MultiplicationExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case "Subtraction":
                        digits.addLast(new SubtractionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    default:
                        throw new UnrecognizedExpressionException(token.getClass().getSimpleName());
                }
            } else {
                throw new UnrecognizedExpressionException("not enough operands");
            }
        }
        float answer = digits.getLast();
        if (digits.size() != 0){
            throw new UnrecognizedExpressionException("not enough operators");
        }
        return answer;
    }
}
