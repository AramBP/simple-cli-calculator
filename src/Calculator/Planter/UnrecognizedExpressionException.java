package Calculator.Planting;

public class UnrecognizedExpressionException extends Exception {
    public UnrecognizedExpressionException(String message){
        super("ERROR - Unrecognized expression: " + message);
    }
}
