package HomeWork3.task7;

import HomeWork3.task6.CalculatorWithCounterInterface;
import HomeWork3.task6.ICalculator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMemory implements ICalculator{
    private double memory;
    private double lastCalculatedValue;
    private ICalculator calc;
    private long countOperation;

    public CalculatorWithMemory(ICalculator calc){
        this.calc = calc;
    }

    /**
     * Данный метод выполняет деление числа a на b
     * @param a число которое нужно разделить
     * @param b число на которое нужно разделить
     * @return результат деления числа a на b. Если b равно нулю вернет ноль.
     */
    @Override
    public double div(double a, double b) {
        counting();
        return this.lastCalculatedValue = this.calc.div(a,b);
    }

    /**
     * Данный метод выполняет умножение числа a на b
     * @param a число которое нужно умножить
     * @param b число на которое нужно умножить
     * @return результат умножения числа a на b.
     */
    @Override
    public double mult(double a, double b) {
        counting();
        return this.lastCalculatedValue = this.calc.mult(a,b);
    }

    /**
     * Данный метод выполняет вычитание числа a на b
     * @param a число из которого нужно вычесть
     * @param b число на которое нужно вычесть
     * @return результат вычитания числа a из числа b.
     */
    @Override
    public double sub(double a, double b) {
        counting();
        return this.lastCalculatedValue = this.calc.sub(a,b);
    }

    /**
     * Данный метод выполняет сложение числа a на b
     * @param a число к которому нужно прибавить
     * @param b число которое нужно прибавить
     * @return результат сложения числа a и b.
     */
    @Override
    public double add(double a, double b) {
        counting();
        return this.lastCalculatedValue = this.calc.add(a,b);
    }

    /**
     * Данный метод выполняет возведение в степень
     * @param number число которое необходимо возвести в степень
     * @param power степень
     * @return результат возведения числа number d степень power.
     */
    @Override
    public double pow(double number, int power) {
        counting();
        return this.lastCalculatedValue = this.calc.pow(number,power);
    }

    /**
     * Данный метод возвращает модуль числа
     * @param a число
     * @return модуль числа
     */
    @Override
    public double abs(double a) {
        counting();
        return this.lastCalculatedValue = this.calc.abs(a);
    }

    /**
     * Данный метод извлекает квадратный корень из числа
     * @param a число
     * @return результат извлечения квадратного корня числа. Если число отрицательное вернет ноль.
     */
    @Override
    public double sqrt(double a) {
        counting();
        return this.lastCalculatedValue = this.calc.sqrt(a);
    }

    /**
     * Данный метод записывает число в память калькулятора
     */
    public void addToMemory(){
        this.memory = this.lastCalculatedValue;
    }

    /**
     * Данный метод возвращает число из памяти калькулятора
     * @return возвращает число из памяти калькулятора, после чего очищает память
     */
    public double getFromMemory(){
        double tmp = this.memory;
        this.memory = 0;
        this.lastCalculatedValue = 0;
        return tmp ;
    }

    /**
     * Данный метод возвращает количество использований калькулятора
     * @return возвращает количество использований калькулятора
     */
    public long getCountOperation() {
        return this.countOperation;
    }

    /**
     * Данный метод считает количество использований калькулятора
     */
    private void counting(){
        ++countOperation;
    }

}
