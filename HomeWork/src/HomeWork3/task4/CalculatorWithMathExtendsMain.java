package HomeWork3.task4;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMathExtendsMain {
    public static void main(String[] args) {
        CalculatorWithMathExtends calculator = new CalculatorWithMathExtends();

        //4.1 + 15 * 7 + (28 / 5) ^ 2
        float part1 = calculator.div(28f, 5f);
        float part2 = calculator.pow(part1, 2);
        float part3 = calculator.mult(15f, 7f);
        float part4 = calculator.add(part2, part3);
        float result = calculator.add(4.1f, part4);

        System.out.println(result);
    }
}
