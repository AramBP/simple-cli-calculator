package Calculator.Tokenizer;

public class InvalidPromptException extends Exception{
    public InvalidPromptException(String message){
        super("ERROR - INVALID PROMPT: " + message);
    }
}
