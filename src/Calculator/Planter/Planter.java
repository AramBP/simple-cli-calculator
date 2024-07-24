package Calculator.Planter;

import Calculator.Parser.Token;
import Calculator.Parser.Token.OperatorType;
import Calculator.Parser.Token.TokenType;
import Calculator.Planter.Expressions.DivisionExpression;
import Calculator.Planter.Expressions.MultiplicationExpression;
import Calculator.Planter.Expressions.PowerOfExpression;
import Calculator.Planter.Expressions.SubtractionExpression;
import Calculator.Planter.Expressions.SumExpression;
import Calculator.Planter.Functions.CosineFunction;
import Calculator.Planter.Functions.LogarithmFunction;
import Calculator.Planter.Functions.NaturalLogarithmFunction;
import Calculator.Planter.Functions.SineFunction;
import Calculator.Planter.Functions.TangentFunction;

import java.util.List;
import java.util.ArrayList;

public class Planter {
    public double Plant(List<Token> sortedTokens) throws UnrecognizedExpressionException{
        List<Double> digits = new ArrayList<>();
        int i =0;
        while (i < sortedTokens.size()){
            Token token = sortedTokens.get(i);
            if (token.tokenType == TokenType.DIGIT){
                digits.addLast(token.value);
            } else if (digits.size() >= 2 && token.tokenType == TokenType.OPERATOR){
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
                        throw new UnrecognizedExpressionException("operator: '" + token.operator + "'");
                }
            } else if (digits.size() >= 1 && token.tokenType == TokenType.FUNCTION){
                double functionArg = digits.removeLast();
                switch (token.functionName) {
                    case "sin":
                        digits.addLast(new SineFunction(functionArg).Calculate());
                        break;
                    case "tan":
                        digits.addLast(new TangentFunction(functionArg).Calculate());
                        break;
                    case "cos":
                        digits.addLast(new CosineFunction(functionArg).Calculate());
                        break;
                    case "log":
                        digits.addLast(new LogarithmFunction(functionArg).Calculate());
                        break;
                    case "ln":
                        digits.addLast(new NaturalLogarithmFunction(functionArg).Calculate());
                        break;
                    default:
                        throw new UnrecognizedExpressionException("function '" + token.functionName +"'");
                }
            } else if (digits.size() == 1 && token.operatorType == OperatorType.UNARY_PRE){
                if (token.operator == '-'){
                    double rightOperand = digits.removeLast();
                    digits.addLast(new SubtractionExpression(0, rightOperand).Calculate());
                } else {
                    throw new UnrecognizedExpressionException("unary prefix operator '" + token.operator + "'");
                }
            } else {
                throw new UnrecognizedExpressionException("not enough operands; number of digits left " + digits.size() + ", operator type " + token.tokenType + ", token: " + token);
            }
            sortedTokens.remove(token);  
        }
        double answer = digits.removeLast();
        if (digits.size() != 0){
            throw new UnrecognizedExpressionException("not enough operators");
        }
        return answer;
    }
}
