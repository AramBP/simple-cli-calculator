package Calculator.Planter.Functions;

public class TangentFunction extends BaseFunction{
    public TangentFunction(double arg){
        super(arg);
    }
    @Override
    public double Calculate(){
        return Math.tan(functionArgument);
    }
}
