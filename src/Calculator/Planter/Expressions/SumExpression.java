package Calculator.Planter.Expressions;

public class SumExpression extends BaseExpression {
    public SumExpression(float left, float right){
        super(left, right);
    }
    @Override
    public float Calculate(){
        return (leftOperand+rightOperand);
    }
}
