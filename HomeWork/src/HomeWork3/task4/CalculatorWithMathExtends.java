package HomeWork3.task4;

import HomeWork3.task2.CalculatorWithOperator;
import HomeWork3.task6.ICalculator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMathExtends extends CalculatorWithOperator implements ICalculator {
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
