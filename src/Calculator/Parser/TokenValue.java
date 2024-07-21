package Calculator.Parser;
import Calculator.Tokenizer.Token;
public class TokenValue {
    public Token ParentToken;
    public TokenValue(Token token){
        this.ParentToken = token;
    }
}
