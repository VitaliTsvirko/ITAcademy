package HomeWork3.task3;

import HomeWork3.task2.CalculatorWithOperator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMathCopy extends CalculatorWithOperator{

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
    public float subt(float a, float b){
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
        return (float) Math.pow(number, power);
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    public float abs (float a){
        return Math.abs(a);
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
    public float sqrt (float a){
        return (float) Math.sqrt(a);
    }
}
