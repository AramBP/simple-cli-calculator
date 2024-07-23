package Calculator.Planter.Expressions;

public class DivisionExpression extends BaseExpression {
    public DivisionExpression(float left, float right){
        super(left,right);
    }
    @Override
    public float Calculate(){
        return (leftOperand / rightOperand);
    }
}
