/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.5
 */

package HomeWork2.sorts;

import HomeWork2.sorts.subtask4.ArrayGenerator;
import HomeWork2.sorts.subtask4.ArraysSorter;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        byte mode;

        System.out.println("Доступны следующие режимы:");
        System.out.println("1 - Предварительно созданные массивы");
        System.out.println("2 - Генерирование массива");
        System.out.println("3 - Генерирование массива (с заданием количества элементов)");
        System.out.println("4 - Ввод массива вручну");

        do {
            System.out.print("Введите выбранный режим: ");
            mode = in.nextByte();
        }while (mode < 1 || mode > 4);

        switch (mode){
            case 1:
                int[] tmp = {1, 2, 3, 4, 5, 6};
                ArraysSorter.sortByAllMethod(tmp);

                tmp = new int[]{1, 1, 1, 1, 1, 1};
                ArraysSorter.sortByAllMethod(tmp);

                tmp = new int[]{9, 1, 5, 99, 9, 9};
                ArraysSorter.sortByAllMethod(tmp);

                tmp = new int[]{};
                ArraysSorter.sortByAllMethod(tmp);
                break;

            case 2:
                ArraysSorter.sortByAllMethod(ArrayGenerator.arrayRandom(false));
                break;

            case 3:
                ArraysSorter.sortByAllMethod(ArrayGenerator.arrayRandom(true));
                break;

            case 4:
                ArraysSorter.sortByAllMethod(ArrayGenerator.arrayFromConsole());
                break;
        }
    }
}

