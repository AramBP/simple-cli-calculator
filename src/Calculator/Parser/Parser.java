package Calculator.Parser;
import java.util.*;

import Calculator.Parser.Token.OperatorType;
import Calculator.Parser.Token.TokenType;

public class Parser {
    public List<Token> parse(String prompt) throws InvalidTokenException{
        List<Token> validTokens = new ArrayList<>();
        List<Token> invalidTokens = new ArrayList<>();
        String numStr;

        for (int i = 0; i < prompt.length(); i++){
            char currentChar = prompt.charAt(i);
            if (Character.isWhitespace(currentChar)){
                continue;
            } else if (isNumeric(currentChar)){
                numStr = new String();
                numStr = Character.toString(currentChar);
                i++;
                while(i < prompt.length() && isNumeric(prompt.charAt(i))){
                    numStr = numStr+ Character.toString(prompt.charAt(i));
                    i++;
                }
                i--;
                try {
                    validTokens.addLast(new Token(Double.valueOf(numStr)));
                } catch (NumberFormatException e){
                    throw new NumberFormatException("ERROR - Invalid Number: '" + numStr + "'");
                }
            }
            else{        
                switch (currentChar) {
                    case '/':
                        validTokens.addLast(new Token(currentChar, OperatorType.BIN_LEFT, 3));
                        break;
                    case '*':
                        validTokens.addLast(new Token(currentChar, OperatorType.BIN_LEFT, 3));
                        break;
                    case '(':
                        validTokens.addLast(new Token(currentChar));
                        break;
                    case ')':
                        validTokens.addLast(new Token(currentChar));
                        break;
                    case '-':
                        // if the list is empty, or if a previous object exists that is either a left bracket or another operator
                        // then '-' is an unary operator
                        if (validTokens.size() == 0 
                        || validTokens.getLast().tokenType == TokenType.OPERATOR
                        || validTokens.getLast().tokenType == TokenType.LEFT_BRACE){
                            validTokens.addLast(new Token(currentChar, OperatorType.UNARY_PRE, 2));
                        } else {
                            validTokens.addLast(new Token(currentChar, OperatorType.BIN_LEFT, 2));
                        }
                        break;
                    case '+':
                        validTokens.addLast(new Token(currentChar, OperatorType.BIN_LEFT, 2));    
                        break;
                    case '^':
                        validTokens.addLast(new Token(currentChar, OperatorType.BIN_RIGHT, 4));
                        break;
                    case 'e':
                        validTokens.addLast(new Token(Math.E));
                        break;
                    case 'p':
                        i++;
                        if (prompt.charAt(i) ==  'i'){
                            validTokens.addLast(new Token(Math.PI));
                        }else{
                            i--;
                        }
                        break;
                    default:
                        String functionName = "" + currentChar;
                        i++;
                        while (i < prompt.length() && Character.isAlphabetic(prompt.charAt(i))){
                            functionName += Character.toString(prompt.charAt(i));
                            i++;
                        }
                        i--;

                        switch (functionName) {
                            case "cos":
                                validTokens.addLast(new Token(functionName));
                                break;
                            case "sin":
                                validTokens.addLast(new Token(functionName));
                                break;
                            case "tan":
                                validTokens.addLast(new Token(functionName));
                                break;  
                            case "log":
                                //if we find log then we want to keep searching for trailing numbers
                                String logBase = new String();
                                i++;
                                while(i < prompt.length() && isNumeric(prompt.charAt(i))){
                                    logBase = logBase + Character.toString(prompt.charAt(i));
                                    i++;
                                }
                                i--;
                                if (logBase.isEmpty()) logBase = "10";
                                validTokens.addLast(new LogarithmToken(functionName, Double.valueOf(logBase)));
                                break; 
                            case "ln":
                                validTokens.addLast(new Token(functionName));
                                break;  
                            case "sqrt":
                                validTokens.addLast(new Token(functionName));
                                break;                   
                            default:
                                throw new InvalidTokenException("" + functionName);
                        }
                }
            }
        }
        if (invalidTokens.size() != 0){
            String errMessage = "invalid value(s) ";
            for (int i = 0; i < invalidTokens.size(); i++){
                errMessage = invalidTokens.get(i).toString() + " ";
            }
            throw new InvalidTokenException(errMessage);
        }

        // implicit multiplication
        List<Token> validTokensClone = new ArrayList<>();
        
        for (int i = 0; i < validTokens.size(); i++){
            Token token = validTokens.get(i);
            if (token.tokenType == TokenType.DIGIT){
                // if previous token is a right brace or a defined constant then add a multiple between the right brace and the digit
                if (validTokensClone.size() > 0 
                && (validTokens.get(i-1).tokenType == TokenType.RIGHT_BRACE || validTokens.get(i-1).isDefinedConstant) ){
                    validTokensClone.addLast(new Token('*', OperatorType.BIN_LEFT, 3));
                    validTokensClone.addLast(token);
                }
                // if the next token is a left brace or a defined constant then add a multiple between the tokens
                else if (validTokens.size() != (i+1) 
                && (validTokens.get(i+1).tokenType == TokenType.LEFT_BRACE || validTokens.get(i+1).isDefinedConstant)){
                    validTokensClone.addLast(token);
                    validTokensClone.addLast(new Token('*', OperatorType.BIN_LEFT, 3));
                } else {
                    validTokensClone.addLast(token);
                }
            } else if (token.tokenType == TokenType.FUNCTION){
                //if the previousfunction is a right brace or a digit then add a multiple between the tokens
                if (validTokensClone.size() > 0 
                && (validTokens.get(i-1).tokenType == TokenType.RIGHT_BRACE || validTokens.get(i-1).tokenType == TokenType.DIGIT)){
                    validTokensClone.addLast(new Token('*', OperatorType.BIN_LEFT, 3));
                    validTokensClone.addLast(token);
                } else {
                    validTokensClone.addLast(token);
                }
            } 
            else if (token.tokenType == TokenType.LEFT_BRACE){
                if (validTokensClone.size() > 1 && validTokens.get(i-1).tokenType == TokenType.RIGHT_BRACE && validTokens.get(i-2).tokenType != TokenType.FUNCTION){
                    validTokensClone.addLast(new Token('*', OperatorType.BIN_LEFT, 3));
                    validTokensClone.addLast(token);
                }
                else {
                    validTokensClone.addLast(token);
                }
            } else {
                validTokensClone.addLast(token);
            }
        }

        return validTokensClone;
    }

    public String parsedInputToString(List<Token> parsedPrompt){
        String s = "";
        for (Token tok : parsedPrompt){
            s += tok.toString() + " ";
        }
        return s;
    }

    private Boolean isNumeric(char c){
        return(Character.isDigit(c) || c == '.');
    }
}
