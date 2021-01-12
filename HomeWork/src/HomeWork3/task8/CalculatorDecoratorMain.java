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
        ICalculator calculator = new CalculatorWithMemoryDecorator(new CalculatorWithCounterDecorator(new CalculatorWithMathExtends()));

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        ((CalculatorWithMemoryDecorator) calculator).addToMemory(calculator.div(28.0, 5.0));
        ((CalculatorWithMemoryDecorator) calculator).addToMemory(calculator.pow(((CalculatorWithMemoryDecorator) calculator).getFromMemory(), 2));
        ((CalculatorWithMemoryDecorator) calculator).addToMemory(calculator.add(((CalculatorWithMemoryDecorator) calculator).getFromMemory(), calculator.mult(15.0, 7.0)));
        double result = calculator.add(4.1, ((CalculatorWithMemoryDecorator) calculator).getFromMemory());

        System.out.println(result);
        System.out.println("Количество использований калькулятора: " + ((CalculatorWithCounterDecorator) ((CalculatorWithMemoryDecorator) calculator).getCalculator()).getCountOperation());
        System.out.println("Последнее сохраненное значение: " + ((CalculatorWithMemoryDecorator) calculator).getLastSaveValue());
    }
}
