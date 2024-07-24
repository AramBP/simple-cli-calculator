package Calculator.Planter.Expressions;

abstract class BaseExpression {
    public double leftOperand;
    public double rightOperand;

    public BaseExpression(double left, double right){
        this.leftOperand = left;
        this.rightOperand = right;
    }
    public abstract double Calculate();
}
