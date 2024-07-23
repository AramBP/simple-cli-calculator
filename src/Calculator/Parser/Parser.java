package Calculator.Parser;
import java.util.ArrayList;
import java.util.List;

import Calculator.Tokenizer.Token;

public class Parser {
    public List<TokenValue> parse(List<Token> tokens) throws InvalidTokenException{
        List<TokenValue> validTokens = new ArrayList<>();
        List<TokenValue> invalidTokens = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++){
            Token tok = tokens.get(i);
            String tokValueStr = tok.value;
            try {
                float tokValue = Float.parseFloat(tok.value);
                validTokens.add(new Digit(tok, tokValue));
            } catch (NumberFormatException e) {
                switch (tokValueStr) {
                    case "/":
                        validTokens.add(new Division(tok));
                        break;
                    case "*":
                        validTokens.add(new Multiplication(tok));
                        break;
                    case "(":
                        validTokens.add(new LeftBrace(tok));
                        break;
                    case ")":
                        validTokens.add(new RightBrace(tok));
                        break;
                    case "-":
                        validTokens.add(new Subtraction(tok));
                        break;
                    case "+":
                        validTokens.add(new Sum(tok));
                        break;
                    default:
                        invalidTokens.add(new TokenValue(tok));
                        break;
                }
            }
        }
        if (invalidTokens.size() != 0){
            String errMessage = "invalid value(s) ";
            for (int i = 0; i < invalidTokens.size(); i++){
                errMessage = invalidTokens.get(i).ParentToken.value + " ";
            }
            throw new InvalidTokenException(errMessage);
        }
        return validTokens;
    }
}
