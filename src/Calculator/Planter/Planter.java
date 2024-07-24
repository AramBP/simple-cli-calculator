package Calculator.Planter;

import Calculator.Parser.Token;
import Calculator.Parser.Token.OperatorType;
import Calculator.Parser.Token.TokenType;
import Calculator.Planter.Expressions.DivisionExpression;
import Calculator.Planter.Expressions.MultiplicationExpression;
import Calculator.Planter.Expressions.PowerOfExpression;
import Calculator.Planter.Expressions.SubtractionExpression;
import Calculator.Planter.Expressions.SumExpression;

import java.util.List;
import java.util.ArrayList;

public class Planter {
    public double Plant(List<Token> sortedTokens) throws UnrecognizedExpressionException{
        List<Double> digits = new ArrayList<>();
        List<Token> operators = new ArrayList<Token>(sortedTokens);

        for (int i = 0; i < sortedTokens.size(); i++){
            Token token = sortedTokens.get(i);
            if (token.tokenType == TokenType.DIGIT){
                digits.add(token.value);
                operators.remove(token);
            }
        }
        for(int i = 0; i < operators.size(); i++){
            Token token = operators.get(i);
            if (digits.size() >= 2) {
                double rightOperand = digits.removeLast();
                double leftOperand = digits.removeLast();
                switch (token.operator) {
                    case '+':
                        digits.addLast(new SumExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case '/':
                        digits.addLast(new DivisionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case '*':
                        digits.addLast(new MultiplicationExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case '-':
                        digits.addLast(new SubtractionExpression(leftOperand, rightOperand).Calculate());
                        break;
                    case '^':
                        digits.addLast(new PowerOfExpression(leftOperand, rightOperand).Calculate());
                        break;
                    default:
                        throw new UnrecognizedExpressionException(token.operator + "");
                }
            } else if (token.operatorType == OperatorType.UNARY_PRE && digits.size() == 1){
                if (token.operator == '-'){
                    double rightOperand = digits.removeLast();
                    digits.addLast(new SubtractionExpression(0, rightOperand).Calculate());
                } else {
                    throw new UnrecognizedExpressionException("unknown unary prefix operator '" + token.operator + "'");
                }
            } else {
                throw new UnrecognizedExpressionException("not enough operands; number of digits left " + digits.size() + ", operator type " + token.tokenType + ", token: " + token);
            }
        }
        double answer = digits.removeLast();
        if (digits.size() != 0){
            throw new UnrecognizedExpressionException("not enough operators");
        }
        return answer;
    }
}
