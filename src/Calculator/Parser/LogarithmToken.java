package Calculator.Parser;

public class LogarithmToken extends Token {
    public double logarithmBase;
    public LogarithmToken(String functionName, double base){
        super(functionName);
        this.logarithmBase = base;
    }
}
