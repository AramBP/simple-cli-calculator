package Calculator.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    public List<Token> splitString(String input) throws InvalidPromptException{
        final char WHITE_SPACE = ' ';
        List<Character> stopCharacters = new ArrayList<>();
        stopCharacters.addAll(Arrays.asList( new Character[] {WHITE_SPACE, '*', '/', '+', '-', '(', ')'}));

        List<Token> tokens = new ArrayList<>();
        int position = 0;
        String nonSymbol= "";
        
        char firstChar = input.charAt(0);
        if (stopCharacters.contains(firstChar) 
            && firstChar != '('){
            throw new InvalidPromptException("first character cannot be an operator or white space");
        }
        
        for (int i = 0; i < input.length(); i++){
            char currentChar = input.charAt(i);

            if (i == input.length()-1){
                //end of string
                if (!stopCharacters.contains(currentChar)){
                    tokens.add(new Token(nonSymbol += currentChar, position));
                } else if (currentChar == WHITE_SPACE) {
                    tokens.add(new Token(nonSymbol, position));
                } else {
                    throw new InvalidPromptException("last character cannot be an operator");
                }
            }

            if (stopCharacters.contains(currentChar)){
                if(nonSymbol.length() > 0){
                    tokens.add(new Token(nonSymbol, position));
                    position += 1;
                    nonSymbol = "";
                }
                if (currentChar != WHITE_SPACE){
                    tokens.add(new Token(String.valueOf(currentChar), position));
                    position += 1;
                }
            } else {
                nonSymbol += String.valueOf(currentChar);
            }
        }

        return tokens;
    }

}
