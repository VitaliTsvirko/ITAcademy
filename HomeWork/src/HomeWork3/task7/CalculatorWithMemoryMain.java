package HomeWork3.task7;

import HomeWork3.task3.CalculatorWithMathCopy;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMemoryMain {
    public static void main(String[] args) {
        CalculatorWithMemory calculator = new CalculatorWithMemory(new CalculatorWithMathCopy());

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        calculator.div(28.0, 5.0);
        calculator.addToMemory();
        calculator.pow(calculator.getFromMemory(), 2);
        calculator.addToMemory();
        calculator.add(calculator.getFromMemory(), calculator.mult(15.0, 7.0));
        calculator.addToMemory();
        double result = calculator.add(4.1, calculator.getFromMemory());

        System.out.println(result);
        System.out.println("Количество использований калькулятора: " + calculator.getCountOperation());
    }
}
