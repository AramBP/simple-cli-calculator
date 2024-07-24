package Calculator.Planter.Expressions;

public class SubtractionExpression extends BaseExpression{
    public SubtractionExpression(double left, double right){
        super(left, right);
    }
    @Override
    public double Calculate(){
        return (leftOperand - rightOperand);
    }
}
