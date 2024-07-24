package Calculator.Planter.Functions;

abstract class BaseFunction {
    double functionArgument;
    
    public BaseFunction(double functionArgument){
        this.functionArgument = functionArgument;
    }

    abstract double Calculate();
}
