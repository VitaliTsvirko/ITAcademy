/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

import static java.lang.Integer.parseInt;

public class Task1Subtask11 {
    public static void main(String[] args) {
        long result = 1L;
        int inputNumber;

        /* Проверка введенных значений */
        if (args.length == 0) {
            System.out.println("Не задан аргумент для расчета факториала");
            return;
        }

        try
        {
            inputNumber = parseInt(args[0]);
        }
        catch ( NumberFormatException e )
        {
            System.out.println("Заданный аргумент не является числом");
            return;
        }

        if (inputNumber < 1 || inputNumber > 20) {
            System.out.println("Задано значение " + inputNumber + ". Задайте значение от 1 до 20");
            return;
        }

        System.out.println("Расчет факториала числа " + inputNumber);
        for (int i = 1; i <= inputNumber - 1; i++){
            System.out.print(i + " * ");
            result *= i;
        }
        result *= inputNumber;
        System.out.println(inputNumber + " = " + result);
    }

    private static void getMaxFactorialNumberForLong(){
        long result = 1L;
        long i = 1L;

        do{
            result *=i;
            System.out.println("Факториал числа " + i + " равен " + result);
            i++;
        }while(result > 0);
        System.out.println("Максимальное значение для long равно " + (i - 2));
    }
}
