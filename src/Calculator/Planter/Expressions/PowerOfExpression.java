package Calculator.Planter.Expressions;

public class PowerOfExpression extends BaseExpression{
    public PowerOfExpression(float left, float right){
        super(left, right);
    }
    @Override
    public float Calculate(){
        return ((float)Math.pow(leftOperand, rightOperand));
    }
}
