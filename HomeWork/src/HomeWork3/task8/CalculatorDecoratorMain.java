package HomeWork3.task8;

import HomeWork3.task3.CalculatorWithMathCopy;
import HomeWork3.task4.CalculatorWithMathExtends;
import HomeWork3.task6.ICalculator;
import HomeWork3.task7.CalculatorWithMemory;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorDecoratorMain {
    public static void main(String[] args) {
        CalculatorWithCounterDecorator counterCalc = new CalculatorWithCounterDecorator(new CalculatorWithMathExtends());
        CalculatorWithMemoryDecorator calculator = new CalculatorWithMemoryDecorator(counterCalc);

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        calculator.addToMemory(calculator.div(28.0, 5.0));
        calculator.addToMemory(calculator.pow(calculator.getFromMemory(), 2));
        calculator.addToMemory(calculator.add(calculator.getFromMemory(), calculator.mult(15.0, 7.0)));
        double result = calculator.add(4.1, calculator.getFromMemory());

        System.out.println(result);
        System.out.println("Количество использований калькулятора: " + counterCalc.getCountOperation());
        System.out.println("Последнее сохраненное значение: " + calculator.getLastSaveValue());
    }
}
