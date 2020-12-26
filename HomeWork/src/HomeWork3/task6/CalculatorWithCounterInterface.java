package HomeWork3.task6;

import HomeWork3.task2.CalculatorWithOperator;
import HomeWork3.task3.CalculatorWithMathCopy;
import HomeWork3.task4.CalculatorWithMathExtends;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithCounterInterface implements ICalculator{
    private long countOperation;
    private ICalculator calc;

    public CalculatorWithCounterInterface(ICalculator calc){
        this.calc = calc;
    }

    /**
     * Данный метод возвращает количество использований калькулятора
     * @return возвращает количество использований калькулятора
     */
    public long getCountOperation(){
        return this.countOperation;
    }

    /**
     * Данный метод выполняет деление числа a на b
     * @param a число которое нужно разделить
     * @param b число на которое нужно разделить
     * @return результат деления числа a на b. Если b равно нулю вернет ноль.
     */
    public double div(double a, double b){
        counting();
        return calc.div(a, b);
    }

    /**
     * Данный метод выполняет умножение числа a на b
     * @param a число которое нужно умножить
     * @param b число на которое нужно умножить
     * @return результат умножения числа a на b.
     */
    public double mult (double a, double b){
        counting();
        return calc.mult(a,b);
    }

    /**
     * Данный метод выполняет вычитание числа a на b
     * @param a число из которого нужно вычесть
     * @param b число на которое нужно вычесть
     * @return результат вычитания числа a из числа b.
     */
    public double sub(double a, double b){
        counting();
        return calc.sub(a,b);
    }

    /**
     * Данный метод выполняет сложение числа a на b
     * @param a число к которому нужно прибавить
     * @param b число которое нужно прибавить
     * @return результат сложения числа a и b.
     */
    public double add (double a, double b){
        counting();
        return calc.add(a,b);
    }

    /**
     * Данный метод выполняет возведение в степень
     * @param number число которое необходимо возвести в степень
     * @param power степень
     * @return результат возведения числа number d степень power.
     */
    public double pow (double number, int power){
        counting();
        return calc.pow(number, power);
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    public double abs (double a){
        counting();
        return calc.abs(a);
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
    public double sqrt (double a){
        counting();
        return calc.sqrt(a);
    }

    /**
     * Данный метод считает количество использований калькулятора
     */
    private void counting(){
        ++countOperation;
    }
}
