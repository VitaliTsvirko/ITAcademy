package HomeWork3.task8;

import HomeWork3.task6.ICalculator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithCounterDecorator implements ICalculator {
    private long countOperation;
    private ICalculator calc;

    public CalculatorWithCounterDecorator(ICalculator calc){
        this.calc = calc;
    }

    /**
     * Данный метод возвращает калькулятор, который был передан в конструкторе
     * @return возвращает калькулятор, который был передан в конструкторе
     */
    public ICalculator getCalculator(){
        return this.calc;
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
        return this.calc.div(a,b);
    }

    /**
     * Данный метод выполняет умножение числа a на b
     * @param a число которое нужно умножить
     * @param b число на которое нужно умножить
     * @return результат умножения числа a на b.
     */
    public double mult (double a, double b){
        counting();
        return this.calc.mult(a,b);
    }

    /**
     * Данный метод выполняет вычитание числа a на b
     * @param a число из которого нужно вычесть
     * @param b число на которое нужно вычесть
     * @return результат вычитания числа a из числа b.
     */
    public double sub(double a, double b){
        counting();
        return this.calc.sub(a,b);
    }

    /**
     * Данный метод выполняет сложение числа a на b
     * @param a число к которому нужно прибавить
     * @param b число которое нужно прибавить
     * @return результат сложения числа a и b.
     */
    public double add (double a, double b){
        counting();
        return this.calc.add(a,b);
    }

    /**
     * Данный метод выполняет возведение в степень
     * @param number число которое необходимо возвести в степень
     * @param power степень
     * @return результат возведения числа number d степень power.
     */
    public double pow (double number, int power){
        counting();
        return this.calc.pow(number, power);
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    public double abs (double a){
        counting();
        return this.calc.abs(a);
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
    public double sqrt (double a){
        counting();
        return this.calc.sqrt(a);
    }

    /**
     * Данный метод считает количество использований калькулятора
     */
    private void counting(){
        ++this.countOperation;
    }
}
