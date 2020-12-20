package HomeWork3.task7;

import HomeWork3.task6.CalculatorWithCounterInterface;
import HomeWork3.task6.ICalculator;

/**
 * Created by Vitali Tsvirko
 */
public class CalculatorWithMemory extends CalculatorWithCounterInterface implements ICalculator{
    private float memory;
    private long countMemoryOperation;

    public CalculatorWithMemory(ICalculator calc){
        super(calc);
    }

    /**
     * Данный метод записывает число в память калькулятора
     * @param number число которое необходимо записать в память
     */
    public void addToMemory(float number){
        countingMemory();
        this.memory = number;
    }

    /**
     * Данный метод возвращает число из памяти калькулятора
     * @return возвращает число из памяти калькулятора, после чего очищает память
     */
    public float getFromMemory(){
        countingMemory();
        float tmp = this.memory;
        this.memory = 0;
        return tmp ;
    }

    /**
     * Данный метод возвращает количество использований калькулятора
     * @return возвращает количество использований всех операций калькулятора
     */
    @Override
    public long getCountOperation() {
        return super.getCountOperation() + this.countMemoryOperation;
    }

    /**
     * Данный метод возвращает количество использований памяти калькулятора
     * @return
     */
    public long getMemoryOperation(){
        return countMemoryOperation;
    }

    /**
     * Данный метод считает количество использований памяти калькулятора
     */
    private void countingMemory(){
        ++this.countMemoryOperation;
    }

}
