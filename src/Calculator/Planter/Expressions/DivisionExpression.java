package Calculator.Planter.Expressions;

public class DivisionExpression extends BaseExpression {
    public DivisionExpression(double left, double right){
        super(left,right);
    }
    @Override
    public double Calculate(){
        return (leftOperand / rightOperand);
    }
}
