/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 * Description:
 */

package HomeWork2.loops;

import static java.lang.Integer.parseInt;

public class Task1Subtask12 {
    public static void main(String[] args) {
        long result;
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
        for (int i = 1; i < inputNumber; i++){
            System.out.print(i + " * ");
        }
        result = factorial(inputNumber);
        System.out.println(inputNumber + " = " + result);
    }

    private static long factorial (int number){
        return (number == 1) ? 1 : number * factorial(number - 1);
    }
}
