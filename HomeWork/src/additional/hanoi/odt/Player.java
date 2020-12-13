
package additional.hanoi.odt;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Vitali Tsvirko
 */
public class Player {

    /**
     * Method returns an array containing the indices of the kernel
     * @return array containing the indices of the kernel. Index 0 source kernel. Index 1 destination kernel.
     *         null if exit
     */
    public int[] requestMove (){
        String userCmd;
        Scanner in = new Scanner(System.in);

        System.out.print("Введите ход (1>2, где 1-откуда, 2-куда,  0-Выход): ");
        userCmd = in.nextLine();

        if(Integer.parseInt(userCmd) == 0){
            return null;
        }
        else {
            return parseCommand(userCmd);
        }
    }

    /**
     * Method returns an array containing the indices of the kernel
     * @param command player command (has the form 1>2)
     * @return array containing the indices of the kernel. Index 0 source kernel. Index 1 destination kernel.
     */
    int[] parseCommand(String command){
        String[] tmp;
        int[] move = new int[2];

        tmp = command.split(">");
        if (Arrays.stream(tmp).count() > 1){
            move[0] = Integer.parseInt(tmp[0]) - 1;
            move[1] = Integer.parseInt(tmp[1]) - 1;
        }
        else {
            System.out.println("Ошибка ввода данных!");
            return null;
        }
        return move;
    }

}
