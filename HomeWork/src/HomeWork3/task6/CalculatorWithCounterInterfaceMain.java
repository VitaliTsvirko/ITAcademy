package HomeWork3.task6;

import HomeWork3.task2.CalculatorWithOperator;
import HomeWork3.task3.CalculatorWithMathCopy;
import HomeWork3.task4.CalculatorWithMathExtends;
import HomeWork3.task5.CalculatorWithCounter;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithCounterInterfaceMain {
    public static void main(String[] args) {
        //CalculatorWithCounterInterface calculator = new CalculatorWithCounterInterface(new CalculatorWithOperator());
        CalculatorWithCounterInterface calculator = new CalculatorWithCounterInterface(new CalculatorWithMathCopy());
        //CalculatorWithCounterInterface calculator = new CalculatorWithCounterInterface(new CalculatorWithMathExtends());

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        double part1 = calculator.div(28.0, 5.0);
        double part2 = calculator.pow(part1, 2);
        double part3 = calculator.mult(15.0, 7.0);
        double part4 = calculator.add(part2, part3);
        double result = calculator.add(4.1, part4);

        System.out.println(result);
        System.out.println("Количество использований калькулятора: " + calculator.getCountOperation());
    }

}
