package Calculator.Planter.Functions;

public class CosineFunction extends BaseFunction{
    public CosineFunction(double arg){
        super(arg);
    }

    @Override
    public double Calculate(){
        return Math.cos(functionArgument);
    }
}
