
package additional.hanoi;

import additional.hanoi.odt.*;
import java.util.Scanner;

/**
 * Created by Vitali Tsvirko
 */
public class HanoiMain {
    public static final int RING_COUNT_MIN = 3;
    public static final int RING_COUNT_MAX = 8;

    public static void main(String[] args) {
        int gameMode;
        int ringCount;
        Player player;
        Filed gameField;
        Publisher publisher;

        gameMode = getGameMode();
        ringCount = getRingCount();

        switch (gameMode){
            case 0:
                return;
            case 1:
                player = new Player();
                break;
            case 2:
                //player = new RecordedGame(MoveRecorder.getMoveRecord(3));
                player = new AutoPlayer(ringCount);
                break;
            default:
                System.out.println("Ошибка");
                return;
        }

        gameField = new Filed(ringCount);
        publisher = new Publisher();

        publisher.printGameField(gameField.getField());

        int[] playerCmd;
        while(true){
            playerCmd = player.requestMove();
            if (playerCmd == null){
                exitGame(0,gameField.moveCounter);
                break;
            }

            int moveStatus = gameField.moveRing(playerCmd[0], playerCmd[1]);

            if (moveStatus == 0){
                publisher.printGameField(gameField.getField());
            }

            if (moveStatus == 1){
                exitGame(1, gameField.moveCounter);
                break;
            }
        }
    }

    /**
     * Method requests a game mode
     * @return game mode: 1 - manual mode, 2 - automatic mode;
     */
    private static int getGameMode() {
        Scanner in = new Scanner(System.in);
        short inputMode;

        do {
            System.out.print("Выбрерите режим игры (0 - Выход, 1 - Ручной, 2 - Автоматический): ");
            inputMode = in.nextShort();
            switch (inputMode) {
                case 0:
                    System.out.println("Выход");
                    System.exit(0);
                    return 0;
                case 1:
                    System.out.println("Выбран ручной режим");
                    return 1;
                case 2:
                    System.out.println("Выбран автоматический режим");
                    return 2;
                default:
                    System.out.println("Ошибка ввода данных");
                    break;
            }
        } while (inputMode != 0 || inputMode != 1 || inputMode != 2);

        return -1;
    }

    /**
     * Method requests ring counts
     * @return ring counts
     */
    private static short getRingCount(){
        Scanner in = new Scanner(System.in);
        short ringCount = -1;

        do {
            System.out.print("Введите количество колец с которыми мы будет играть (от 3 до 8. Для выхода введите 0): ");
            ringCount = in.nextShort();

            if (ringCount == 0){
                System.out.println("Выход");
                return 0;
            }

            if (ringCount >= RING_COUNT_MIN && ringCount <= RING_COUNT_MAX) {
                System.out.println("Будем играть с " + ringCount + " кольцами");
                return ringCount;
            }
            else {
                System.out.println("Ошибка ввода данных");
            }
        } while (ringCount < RING_COUNT_MIN || ringCount > RING_COUNT_MAX);

        System.out.println("Выход");
        System.exit(0);
        return -1;
    }

    private static void exitGame(int gameState, int moveCount){
        System.out.println("Игра окончена");
        if (gameState == 1) {
            System.out.println("Поздравляем, вы выйграли!");
        }
        System.out.println("Общее количество ходов: " + moveCount);
    }
}
