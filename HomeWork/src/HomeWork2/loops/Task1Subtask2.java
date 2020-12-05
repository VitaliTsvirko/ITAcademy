/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

import static java.lang.Long.parseLong;

public class Task1Subtask2 {
    public static void main(String[] args) {
        long inputNumber;
        long result;
        int  digitCount;

        /* Проверка введенных значений */
        if (args.length == 0) {
            System.out.println("Не задан аргумент для расчета");
            return;
        }

        System.out.println("Введено число: " + args[0]);
        try {
            inputNumber = parseLong(args[0]);
        }
        catch ( NumberFormatException e ) {
            System.out.println("Заданный аргумент не является числом");
            return;
        }

        result = getMultiplicationOfAllDigits(inputNumber);

        digitCount = args[0].length();
        for (int i = digitCount; i > 1; i--) {
            int digit;
            digit = (int) (inputNumber / (Math.pow(10, i - 1 )));
            System.out.print(digit + " * ");
            inputNumber -= digit * (Math.pow(10, i - 1 ));
        }

        System.out.print(inputNumber);
        System.out.println(" = " + result);
    }

    private static long getMultiplicationOfAllDigits(long number){
        return (number == 0) ? 1 : ((number % 10) * getMultiplicationOfAllDigits(number / 10) );
    }
}
