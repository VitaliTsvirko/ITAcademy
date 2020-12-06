/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.6
 */

package HomeWork2.sorts.subtask4;

import java.util.Random;
import java.util.Scanner;

public class ArrayGenerator {

    public static int[] arrayRandom(boolean isNeedRequestSize){
        Random Random = new Random();
        int[] arr = new int[Random.nextInt(20) + 1];

        if (isNeedRequestSize){
            Scanner in = new Scanner(System.in);
            int size;
            do {
                System.out.print("Введите количество элементов массива: ");
                size = in.nextInt();

            }while (size < 0);

             arr = new int[size];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Random.nextInt(100);
        }

        return arr;
    }

    public static int[] arrayFromConsole(){
        Scanner in = new Scanner(System.in);
        int inputArraySize;

        do {
            System.out.print("Введите количетво элементов массива: ");
            inputArraySize = in.nextInt();
        }while (inputArraySize < 1);

        int[] arr = new int[inputArraySize];
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            arr[i] = in.nextInt();
        }

        return arr;
    }
}
