package HomeWork3.task4;

import HomeWork3.task2.CalculatorWithOperator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMathExtends extends CalculatorWithOperator {
    public float power (float number, int power){
        return (float) Math.pow(number, power);
    }

    public float abs (float a){
        return Math.abs(a);
    }

    public float sqrt (float a, float b){
        return (float) Math.sqrt(a);
    }
}
