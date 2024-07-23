package Calculator.Planting;

public class SubtractionExpression extends BaseExpression{
    public SubtractionExpression(float left, float right){
        super(left, right);
    }
    @Override
    public float Calculate(){
        return (leftOperand - rightOperand);
    }
}
