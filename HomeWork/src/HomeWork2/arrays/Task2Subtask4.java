/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.5
 */

package HomeWork2.arrays;

import java.util.Arrays;
import java.util.Random;

public class Task2Subtask4 {
    public static void main(String[] args) {
        int size = 10;
        int maxValueExclusion = 655;

        int[] container = arrayRandom(size, maxValueExclusion);
        System.out.println("Сгенерированный массив: " + Arrays.toString(container));

        System.out.println("Сумма четных положительных элементов массива: " + getSumEvenPositiveArrayElements(container));
        System.out.println("Максимальный из элементов массива с четными индексами: " + getMaximumOfEvenIndexedArrayElement(container));
        System.out.println("Элементы массива, которые меньше среднего арифметического: " + Arrays.toString(getArrayElementsThatAreLessThanAverage(container)));
        System.out.println("Два минимальных элемента массива: " + Arrays.toString(getTwoMinArraysElements(container)));
        System.out.println(String.format("Сжатый массив (Элементы в диапазоне [%d,%d] заменены нулями): %s",
                            container[0], container[1],
                            Arrays.toString(compressArrayByDeleteElementsInRange(container[0], container[1], container))));

        System.out.println("Сумма цифр массива: " + getSumArrayDigits(container));
    }

    public static int[] arrayRandom(int size, int maxValueExclusion){
        Random Random = new Random();
        int [] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = Random.nextInt(maxValueExclusion);
        }

        return arr;
    }

    /**
     *
     * @param arr - массив чисел
     * @return Сумма четных положительных элементов массива
     */
    public static int getSumEvenPositiveArrayElements (int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] % 2 == 0) && (arr[i] > 0 )){
                sum += arr[i];
            }
        }
        return sum;
    }

    /**
     *
     * @param arr - массив чисел
     * @return Максимальный из элементов массива с четными индексами
     */
    public static int getMaximumOfEvenIndexedArrayElement (int[] arr){
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] > max) && (i % 2 == 0)) {
                max = arr[i];
            }
        }

        return max;
    }

    /**
     *
     * @param arr - массив чисел
     * @return Элементы массива, которые меньше среднего арифметического
     */
    public static int[] getArrayElementsThatAreLessThanAverage (int arr[]){
        float average;
        int sum = 0;
        int[] res = new int[arr.length];

        for (int element : arr) {
            sum += element;
        }

        average = sum / arr.length;

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < average) {
                res[j++] = arr[i];
            }
        }

        res = Arrays.copyOf(res, j);

        return res;
    }

    /**
     *
     * @param arr - массив чисел
     * @return два наименьших (минимальных) элемента массива
     */
    public static int[] getTwoMinArraysElements (int[] arr){
        int[] res = {0,1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[res[0]]) {
                res[0] = i;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] < arr[res[1]]) && (res[0] != i)) {
                res[1] = i;
            }
        }

        res[0] = arr[res[0]];
        res[1] = arr[res[1]];

        return res;
    }

    /**
     *
     * @param rangeMin -
     * @param rangeMax
     * @param arr
     * @return массив, удалив элементы в интервале [rangeMin, rangeMax] на нули
     */
    public static int[] compressArrayByDeleteElementsInRange (int rangeMin, int rangeMax, int[] arr){
        int[] res = new int[arr.length];
        int newArrayLength = 0;
        int _rangeMin = rangeMin;
        int _rangeMax = rangeMax;

        //проверка rangeMin >= rangeMax;
        if (rangeMax < rangeMin){
            _rangeMax = rangeMin;
            _rangeMin = rangeMax;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < _rangeMin || arr[i] > _rangeMax) {
                res[newArrayLength++] = arr[i];
            }
        }

        res = Arrays.copyOf(res, newArrayLength);

        return res;
    }

    /**
     *
     * @param arr массив
     * @return Сумма всех цифр массива
     */
    public static long getSumArrayDigits (int[] arr){
        long sum = 0L;

        for (int i = 0; i < arr.length; i++) {
            sum += getSumOfAllDigits(arr[i]);
        }

        return sum;
    }

    /**
     *
     * @param number число
     * @return сумма всех цифр числа
     */
    private static long getSumOfAllDigits(long number){
        return (number == 0) ? 0 : ((number % 10) + getSumOfAllDigits(number / 10) );
    }
}
