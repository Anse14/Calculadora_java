package calculadora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Node calc = new Node();
        Scanner consoleIn = new Scanner(System.in);
        String expression = consoleIn.nextLine();
        calc.set_expression(expression);
        calc.operate();
        System.out.println(calc.get_value());
    }
}
