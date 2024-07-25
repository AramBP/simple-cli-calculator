package Calculator.Planter.Functions;

public class SquareRootFunction extends BaseFunction {
    public SquareRootFunction(double arg){
        super(arg);
    }
    @Override
    public double Calculate(){
        return Math.sqrt(functionArgument);
    }
}
