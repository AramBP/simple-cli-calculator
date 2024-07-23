package Calculator.Planter;

public class PowerOfExpression extends BaseExpression{
    public PowerOfExpression(float left, float right){
        super(left, right);
    }
    @Override
    public float Calculate(){
        return ((float)Math.pow(leftOperand, rightOperand));
    }
}
