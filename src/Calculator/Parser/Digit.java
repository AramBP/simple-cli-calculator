package Calculator.Parser;
import Calculator.Tokenizer.Token;

public class Digit extends TokenValue {
    public int value;
    public Digit(Token token, int value){
        super(token);
        this.value = value;
    }
}
