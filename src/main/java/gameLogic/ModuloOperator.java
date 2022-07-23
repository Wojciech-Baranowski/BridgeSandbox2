package gameLogic;

public class ModuloOperator {

    public static int modAdd(int number1, int number2, int mod) {
        return (number1 + number2) % mod;
    }

    public static int modSub(int number1, int number2, int mod) {
        return ((number1 % mod) - (number2 % mod) + mod) % mod;
    }

    private ModuloOperator() {
    }
}
