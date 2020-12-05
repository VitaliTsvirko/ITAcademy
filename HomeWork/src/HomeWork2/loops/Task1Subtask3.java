/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

import java.util.Scanner;

public class Task1Subtask3 {
    public static void main(String[] args) {
        float inputNumber;
        int inputPower;
        float result;
        Scanner in = new Scanner(System.in);

        System.out.print("Введите число: ");
        inputNumber = in.nextFloat();

        do {
            System.out.print("Введите степень: ");
            inputPower = in.nextInt();
            if (inputPower < 1) {
                System.out.println("Степень должна быть положительной");
            }
        }while (inputPower < 1);

        result = inputNumber;
        for (int i = 1; i < inputPower; i++) {
            result *= inputNumber;
        }
        System.out.println("Результат:" + result);
    }
}
