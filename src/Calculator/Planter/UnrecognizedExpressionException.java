package Calculator.Planter;

public class UnrecognizedExpressionException extends Exception {
    public UnrecognizedExpressionException(String message){
        super("ERROR - Unrecognized : " + message);
    }
}
