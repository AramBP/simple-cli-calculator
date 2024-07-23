package Calculator.Parser;
import Calculator.Tokenizer.Token;

public class Digit extends TokenValue {
    public float value;
    public Digit(Token token, float value){
        super(token);
        this.value = value;
    }
}
