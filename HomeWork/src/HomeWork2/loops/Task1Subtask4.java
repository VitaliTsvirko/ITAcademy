/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

import java.util.Scanner;

public class Task1Subtask4 {
    public static void main(String[] args) {
        long a = 1L;
        long result = a;
        long nextVal = a;
        long b;
        long counter = 1;
        boolean overflow = false;

        Scanner in = new Scanner(System.in);
        System.out.print("Введите число:");
        b = in.nextLong();

        do {
            nextVal *= (a * b);

            if (nextVal / result != b){
                overflow = true;
            }
            else {
                result *= (a * b);
                ++counter;
            }
        }while (!overflow);

        System.out.println("До переполнения: " + result);
        System.out.println("После переполнения: " + nextVal);
        System.out.println("Количество итераций: " + counter);
    }
}
