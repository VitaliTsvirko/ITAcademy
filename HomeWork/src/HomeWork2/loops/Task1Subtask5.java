/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

public class Task1Subtask5 {
    public static void main(String[] args) {
        printMultiplicationTable(2,5);
        System.out.println("===================================================================");
        printMultiplicationTable(6,9);
    }


    /**
     * Данный метод выводит в консоль  таблицу умножения
     * @param rangeMin начало диапазона чисел для которого выводить таблицу
     * @param rangeMax конец диапазона чисел для которого выводить таблицу (включительно)
     */
    public static void printMultiplicationTable (int rangeMin, int rangeMax){
        for (int i = 1; i <= 10; i++) {
            for (int j = rangeMin; j <= rangeMax; j++) {
                if (j == rangeMax){
                    System.out.print(String.format("%2d * %2d = %2d", j, i, j * i));
                }else {
                    System.out.print(String.format("%2d * %2d = %2d  ||  ", j, i, j * i));
                }
            }
            System.out.println();
        }

    }

}
