package HomeWork3.task2;

import HomeWork3.task6.ICalculator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithOperator implements ICalculator {

    /**
     * Данный метод выполняет деление числа a на b
     * @param a число которое нужно разделить
     * @param b число на которое нужно разделить
     * @return результат деления числа a на b. Если b равно нулю вернет ноль.
     */
    public float div(float a, float b){
        return (b != 0) ? a / b : 0;
    }

    /**
     * Данный метод выполняет умножение числа a на b
     * @param a число которое нужно умножить
     * @param b число на которое нужно умножить
     * @return результат умножения числа a на b.
     */
    public float mult (float a, float b){
        return a * b;
    }

    /**
     * Данный метод выполняет вычитание числа a на b
     * @param a число из которого нужно вычесть
     * @param b число на которое нужно вычесть
     * @return результат вычитания числа a из числа b.
     */
    public float sub(float a, float b){
        return a - b;
    }

    /**
     * Данный метод выполняет сложение числа a на b
     * @param a число к которому нужно прибавить
     * @param b число которое нужно прибавить
     * @return результат сложения числа a и b.
     */
    public float add (float a, float b){
        return a + b;
    }

    /**
     * Данный метод выполняет возведение в степень
     * @param number число которое необходимо возвести в степень
     * @param power степень
     * @return результат возведения числа number d степень power.
     */
    public float pow (float number, int power){
        float result = 1;

        for (int i = 0; i < abs(power); i++) {
            result *= number;
        }

        return (power < 0) ? 1 / result : result;
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    public float abs (float a){
        return (a < 0) ? -a : a;
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
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
