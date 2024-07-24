package Calculator.Planter.Expressions;

public class MultiplicationExpression extends BaseExpression{
    public MultiplicationExpression(double left, double right){
        super(left, right);
    }
    @Override
    public double Calculate(){
        return (leftOperand * rightOperand);
    }
}
