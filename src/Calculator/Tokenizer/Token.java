package Calculator.Tokenizer;

public class Token {
    public String value;
    public int position;

    public Token(String value, int position){
        this.value = value;
        this.position = position;

    }
    public Boolean equals(Token token){
        if (token == null){
            return false;
        }
        if (token.value == this.value && token.position == this.position ){
            return true;
        } else {
            return false;
        }
    }
}
