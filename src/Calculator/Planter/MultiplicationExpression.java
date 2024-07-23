package Calculator.Planter;

public class MultiplicationExpression extends BaseExpression{
    public MultiplicationExpression(float left, float right){
        super(left, right);
    }
    @Override
    public float Calculate(){
        return (leftOperand * rightOperand);
    }
}
