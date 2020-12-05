/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.4
 */

package HomeWork2.loops;

public class Task1Subtask5 {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            for (int j = 2; j < 6; j++) {
                if (j == 5){
                    System.out.print(String.format("%2d * %2d = %2d", j, i, j * i));
                }else {
                    System.out.print(String.format("%2d * %2d = %2d  ||  ", j, i, j * i));
                }
            }
            System.out.println();
        }

        System.out.println("===================================================================");

        for (int i = 1; i < 11; i++) {
            for (int j = 6; j < 10; j++) {
                if (j == 9){
                    System.out.print(String.format("%2d * %2d = %2d", j, i, j * i));
                }else {
                    System.out.print(String.format("%2d * %2d = %2d  ||  ", j, i, j * i));
                }
            }
            System.out.println();
        }


    }
}
