package Calculator.Planter.Expressions;

public class PowerOfExpression extends BaseExpression{
    public PowerOfExpression(double left, double right){
        super(left, right);
    }
    @Override
    public double Calculate(){
        return (Math.pow(leftOperand, rightOperand));
    }
}
