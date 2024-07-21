package Calculator.Tokenizer;

public class Token {
    public String value;
    public int position;
    public String type = "Number";

    public Token(String value, int position){
        this.value = value;
        this.position = position;
        try {
            Integer.valueOf(value);
        } catch (NumberFormatException e) {
            type = "Identifier";
        }
    }

    public Token(String value, int position, String type){
        this.value = value;
        this.position = position;
        this.type = type;
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
