package HomeWork3.task2;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithOperator {

    public float div(float a, float b){
        return (b != 0) ? a / b : 0;
    }

    public float mult (float a, float b){
        return a * b;
    }

    public float sub(float a, float b){
        return a - b;
    }

    public float add (float a, float b){
        return a + b;
    }

    public float pow (float number, int power){
        float result = 1;

        for (int i = 0; i < abs(power); i++) {
            result *= number;
        }

        return (power < 0) ? 1 / result : result;
    }

    public float abs (float a){
        return (a < 0) ? -a : a;
    }

    public float sqrt (float a){
        if (a < 0 ) {
            return 0.0f;
        }

        float precision = 0.000000001f;
        float tmp = a;

        while ((tmp - a / tmp) > precision)
        {
            tmp = (tmp + a / tmp) / 2.0f;
        }
        return tmp;
    }
}
