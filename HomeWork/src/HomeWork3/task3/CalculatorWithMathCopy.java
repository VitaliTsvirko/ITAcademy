package HomeWork3.task3;

import HomeWork3.task2.CalculatorWithOperator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMathCopy {
    public float div(float a, float b){
        return (b != 0) ? a / b : 0;
    }

    public float mult (float a, float b){
        return a * b;
    }

    public float subt(float a, float b){
        return a - b;
    }

    public float add (float a, float b){
        return a + b;
    }

    public float pow (float number, int power){
        return (float) Math.pow(number, power);
    }

    public float abs (float a){
        return Math.abs(a);
    }

    public float sqrt (float a, float b){
        return (float) Math.sqrt(a);
    }
}
