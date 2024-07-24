package Calculator.Planter.Functions;

public class LogarithmFunction extends BaseFunction{
    public LogarithmFunction(double arg){
        super(arg);
    }
    @Override
    public double Calculate(){
        return Math.log10(functionArgument);
    }
}
