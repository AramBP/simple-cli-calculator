package Calculator.Sorter;

public class UnrecognizedTokenException extends Exception{
    public UnrecognizedTokenException(String message){
        super("ERROR - Unrecognized token: " + message);
    }
}
