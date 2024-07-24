package Calculator.Planter.Functions;

public class NaturalLogarithmFunction extends BaseFunction {
    public NaturalLogarithmFunction(double arg){
        super(arg);
    }
    @Override
    public double Calculate(){
        return Math.log(functionArgument);
    }
}
