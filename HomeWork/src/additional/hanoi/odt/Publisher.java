
package additional.hanoi.odt;

/**
 * Created by Vitali Tsvirko
 */
public class Publisher {

    public void printGameField(int[][] field){
        for (int i = 0; i < field[0].length; i++) {
            for (int k = 0; k < field.length; k++) {
                if (field[k][i] == 0) {
                    System.out.print("* ");
                }
                else {
                    System.out.print(field[k][i] + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
