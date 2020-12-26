package HomeWork3.task5;

import HomeWork3.task2.CalculatorWithOperator;
import HomeWork3.task3.CalculatorWithMathCopy;
import HomeWork3.task4.CalculatorWithMathExtends;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithCounter {
    private long countOperation;
    private CalculatorWithOperator calc1;
    private CalculatorWithMathCopy calc2;

    
    public CalculatorWithCounter(CalculatorWithOperator calc){
        this.calc1 = calc;
        this.calc2 = null;
    }

    public CalculatorWithCounter(CalculatorWithMathCopy calc){
        this.calc1 = null;
        this.calc2 = calc;
    }

    public CalculatorWithCounter(CalculatorWithMathExtends calc){
        this.calc1 = calc;
        this.calc2 = null;
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
        if (calc1 != null) {
            return calc1.div(a,b);
        }
        return calc2.div(a,b);
    }

    /**
     * Данный метод выполняет умножение числа a на b
     * @param a число которое нужно умножить
     * @param b число на которое нужно умножить
     * @return результат умножения числа a на b.
     */
    public double mult (double a, double b){
        counting();
        if (calc1 != null) {
            return calc1.mult(a,b);
        }
        return calc2.mult(a,b);
    }

    /**
     * Данный метод выполняет вычитание числа a на b
     * @param a число из которого нужно вычесть
     * @param b число на которое нужно вычесть
     * @return результат вычитания числа a из числа b.
     */
    public double sub(double a, double b){
        counting();
        if (calc1 != null) {
            return calc1.sub(a,b);
        }
        return calc2.sub(a,b);
    }

    /**
     * Данный метод выполняет сложение числа a на b
     * @param a число к которому нужно прибавить
     * @param b число которое нужно прибавить
     * @return результат сложения числа a и b.
     */
    public double add (double a, double b){
        counting();
        if (calc1 != null) {
            return calc1.add(a,b);
        }
        return calc2.add(a,b);
    }

    /**
     * Данный метод выполняет возведение в степень
     * @param number число которое необходимо возвести в степень
     * @param power степень
     * @return результат возведения числа number d степень power.
     */
    public double pow (double number, int power){
        counting();
        if (calc1 != null) {
            return calc1.pow(number, power);
        }
        return calc2.pow(number, power);
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    public double abs (double a){
        counting();
        if (calc1 != null) {
            return calc1.abs(a);
        }
        return calc2.abs(a);
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
    public double sqrt (double a){
        counting();
        if (calc1 != null) {
            return calc1.sqrt(a);
        }
        return calc2.sqrt(a);
    }

    /**
     * Данный метод считает количество использований калькулятора
     */
    private void counting(){
        ++countOperation;
    }

}
