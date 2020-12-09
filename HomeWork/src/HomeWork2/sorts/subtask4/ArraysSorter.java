/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.5
 */

package HomeWork2.sorts.subtask4;

import java.util.Arrays;

public class ArraysSorter {

    public static void sortByAllMethod (int[] arr){
        bubbleSort(arr);
        shakerSort(arr);
    }

    public static int[] bubbleSort (int[] arr){
        int[] res = Arrays.copyOf(arr, arr.length);
        boolean isSort = false;

        System.out.print("Пузырьковая сортировка: ");
        while (!isSort){
            isSort = true;
            for (int i = 0; i < res.length - 1; i++) {
                if (res[i] > res[i + 1]) {
                    swapArrayElement(i, (i + 1), res);

                    isSort = false;
                }
            }
        }

        printTwoArrays(arr, res);
        return res;
    }

    public static int[] shakerSort(int arr[]) {
        int[] res = Arrays.copyOf(arr, arr.length);
        boolean isSort = false;

        int tmp;
        int left = 0;
        int right = res.length - 1;

        System.out.print("Шейкерная сортировка: ");
        do {
            isSort = true;
            for (int i = left; i < right; i++) {
                if (res[i] > res[i + 1]) {
                    swapArrayElement(i, (i + 1), res);
                    isSort = false;
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (res[i] < res[i - 1]) {
                    swapArrayElement(i, (i - 1), res);
                    isSort = false;
                }
            }
            left++;

        } while (left < right && !isSort);

        printTwoArrays(arr, res);

        return res;
    }

    private static void swapArrayElement (int index1, int index2, int[] arr){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printTwoArrays (int[] sourceArray, int[] sortArray){
        System.out.println(Arrays.toString(sourceArray) + " -> " + Arrays.toString(sortArray));
    }

}
