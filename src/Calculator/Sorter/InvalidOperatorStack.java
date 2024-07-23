package Calculator.Sorter;

public class InvalidOperatorStack extends Exception {
    public InvalidOperatorStack(String message){
        super("ERROR - non operator on shunting stack " + message);
    }
}
