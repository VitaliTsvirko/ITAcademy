/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.5
 */

package HomeWork2.arrays;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task2Subtask3 {
    public static void main(String[] args) {
        int size;
        int maxValueExclusion;
        Scanner in = new Scanner(System.in);

        do {
            System.out.print("Введите количество элементов массива: ");
            size = in.nextInt();
        }while (size < 0);

        do {
            System.out.print("Введите максимальное число для генерирования: ");
            maxValueExclusion = in.nextInt();
        }while (maxValueExclusion < 0);

        int[] container = arrayRandom(size, maxValueExclusion);
        System.out.println("Сгенерированный массив: " + Arrays.toString(container));
    }

    public static int[] arrayRandom(int size, int maxValueExclusion){
        Random Random = new Random();
        int [] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = Random.nextInt(maxValueExclusion);
        }

        return arr;
    }
}
