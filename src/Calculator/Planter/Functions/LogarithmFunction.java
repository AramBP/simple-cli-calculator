package Calculator.Planter.Functions;

public class LogarithmFunction extends BaseFunction{
    double logarithmBase;
    public LogarithmFunction(double arg, double base){
        super(arg);
        this.logarithmBase = base;
    }
    @Override
    public double Calculate(){
        return (Math.log(functionArgument)/Math.log(logarithmBase));
    }
}
