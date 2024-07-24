package Calculator.Planter.Expressions;

public class SumExpression extends BaseExpression {
    public SumExpression(double left, double right){
        super(left, right);
    }
    @Override
    public double Calculate(){
        return (leftOperand+rightOperand);
    }
}
