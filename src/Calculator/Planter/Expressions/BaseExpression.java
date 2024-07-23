package Calculator.Planter.Expressions;

abstract class BaseExpression {
    public float leftOperand;
    public float rightOperand;

    public BaseExpression(float left, float right){
        this.leftOperand = left;
        this.rightOperand = right;
    }
    public abstract float Calculate();
}
