package HomeWork3.task2;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithOperatorMain {
    public static void main(String[] args) {
        CalculatorWithOperator calculator = new CalculatorWithOperator();

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        double part1 = calculator.div(28.0, 5.0);
        double part2 = calculator.pow(part1, 2);
        double part3 = calculator.mult(15.0, 7.0);
        double part4 = calculator.add(part2, part3);
        double result = calculator.add(4.1, part4);

        System.out.println(result);
    }
}
