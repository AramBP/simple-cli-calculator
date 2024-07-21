package Calculator.Parser;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message){
        super("ERROR - Invalid Token: " + message);
    }
}
