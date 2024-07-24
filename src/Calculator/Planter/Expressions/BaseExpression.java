package Calculator.Planter.Expressions;

abstract class BaseExpression {
    double leftOperand;
    double rightOperand;

    public BaseExpression(double left, double right){
        this.leftOperand = left;
        this.rightOperand = right;
    }
    public abstract double Calculate();
}
