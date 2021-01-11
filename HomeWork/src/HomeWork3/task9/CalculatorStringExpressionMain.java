package HomeWork3.task9;

import calc.ExpressionUtils;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorStringExpressionMain {
    public static void main(String[] args) {
        CalculatorStringExpression calculator = new CalculatorStringExpression();
        String[] expressionsArray = {"1 + 1",
                                    "(1 + 100) / 10",
                                    "(2 * 2 + 3) * |1 - 100|^2",
                                    "((2 + 2) / 3 + 2^|2 - 4|) * 100",
                                    "((-2.12 + -0.23) / 3 + 2^|2 - 4|) * 100",
                                    "|2.12345 - PI| + |-E|^10 * 12.345 + (1.12 - 0.123) / ((12.12 + 12.12) * 12)",
                                    "|3 - PI| + |-E|^10 * 12.123",
                                    "3+.3333333333333",
                                    "(3)",
                                    "3+3+",
                                    "(3 + 3) + 3)",
                                    "|3 - 7| + |4 -",
                                    "(",
                                    "(1+",
                                    "+12+12",
                                    "4.1 + 15 * 7 + (28 / 5) ^ 2"};


        for(String expression : expressionsArray) {
            try {
                System.out.println("\nВыражение: " + expression);
                System.out.println("Результат: " + calculator.calculateStringExpression(expression));
            } catch (Exception e) {
                System.out.println("Ошибка в выражении (" + e.getMessage() + ")");
            }
        }
    }

}
