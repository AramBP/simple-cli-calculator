package Calculator.Planter.Functions;

public class SineFunction extends BaseFunction{
    public SineFunction(double arg){
        super(arg);
    }
    @Override
    public double Calculate(){
        return Math.sin(functionArgument);
    }
}
