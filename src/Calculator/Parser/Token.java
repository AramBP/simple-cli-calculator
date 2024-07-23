package Calculator.Parser;

public class Token {
    public char operator;
    public float value;
    public int precedence;
    public OperatorType operatorType;
    public TokenType tokenType;

    public enum OperatorType {UNARY_POST, UNARY_PRE, BIN_LEFT, BIN_RIGHT};
    public enum TokenType {DIGIT, OPERATOR, LEFT_BRACE, RIGHT_BRACE};


    public Token(float value){
        this.value = value;
        this.tokenType = TokenType.DIGIT;
    }

    public Token(char operator){
        if (operator == '('){
            this.operator = operator;
            this.tokenType = TokenType.LEFT_BRACE;
        } else if (operator == ')'){
            this.operator = operator;
            this.tokenType = TokenType.RIGHT_BRACE;
        }
    }

    public Token(char operator, OperatorType operatorType, int precedence){
        this.operator = operator;
        this.tokenType = TokenType.OPERATOR;
        this.operatorType = operatorType;
        this.precedence = precedence;
    }

    public String toString(){
        if (this.tokenType == TokenType.DIGIT){
            return Float.toString(this.value);
        } else {
            return (Character.toString(this.operator));
        }
    }
}
