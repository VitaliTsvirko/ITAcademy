/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.5
 */

package HomeWork2.arrays;

import java.util.Scanner;

public class Task2Subtask12 {
    public static void main(String[] args) {
        int[] arr  = arrayFromConsole();

        printArrayToConsole(arr);
        printEverySecondElementToConsole(arr);
        printReverseArrayToConsole(arr);
    }

    public static int[] arrayFromConsole(){
        Scanner in = new Scanner(System.in);
        int inputArrayCount;

        do {
            System.out.print("Введите количетво элементов массива: ");
            inputArrayCount = in.nextInt();
        }while (inputArrayCount < 1);

        int[] arr = new int[inputArrayCount];
        for (int i = 0; i < inputArrayCount; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            arr[i] = in.nextInt();
        }

        return arr;
    }

    public static void printArrayToConsole(int[] arr){
        System.out.print("Массив: [");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length -1] + "]\n");
    }

    public static void printEverySecondElementToConsole(int[] arr){
        int i = 1;

        if (arr.length == 1) {
            System.out.println("Массив состоит из одного элемента");
            return;
        }

        System.out.print("Каждый второй элемент массива: ");
        while (i < arr.length){
            if (i + 2 < arr.length) {
                System.out.print(arr[i] + ", ");
            }
            else {
                System.out.print(arr[i] + "\n");
            }
            i += 2;
        }
    }

    public static void printReverseArrayToConsole (int[] arr){
        System.out.print("Массив в обратной последовательности: ");
        for (int i = arr.length - 1; i > 0; i--) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[0] + "\n");
    }
}
