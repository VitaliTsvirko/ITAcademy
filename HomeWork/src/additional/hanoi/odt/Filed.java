
package additional.hanoi.odt;

/**
 * Created by Vitali Tsvirko
 */
public class Filed {
    public int[][] field;
    public int moveCounter;

    public Filed(int ringCount){
        this.field = new int[3][ringCount];
        initField();
    }

    /**
     * Method return field data
     * @return array [kernels][rings]
     */
    public int[][] getField() {
        return field;
    }

    /**
     * Method initialize game filed
     */
    public void initField(){
        moveCounter = 0;
        for (int i = 0; i < field[0].length; i++) {
            for (int k = 0; k < field.length; k++) {
                field[k][i] = (k == 0) ? i + 1 : 0;
            }
        }
    }

    /**
     * Move ring
     * @param fromKernel kernel index from which we move
     * @param toKernel kernel index to which we move
     * @return moving status 0 - OK, -1 - moving impossible
     */
    public int moveRing (int fromKernel, int toKernel) {
        System.out.println("Moving");

        int sourceRingIndex = getUpRingIndex(fromKernel);
        int destinationRingIndex = getUpRingIndex(toKernel);
        int destinationFreeIndex = getFreeKernelIndex(destinationRingIndex);

        if (isMovePossible(fromKernel, toKernel, sourceRingIndex, destinationRingIndex)) {
            System.out.println(String.format("Перемещаем кольцо со стержня %d на стержень %d", fromKernel + 1, toKernel + 1 ));
            field[toKernel][destinationFreeIndex]  = field[fromKernel][sourceRingIndex];
            field[fromKernel][sourceRingIndex] = 0;

            MoveRecorder.moveRecord(String.format("%d>%d", fromKernel + 1, toKernel + 1));

            ++moveCounter;
            return 0;
        }

        if (isGameOver()){
            return 1;
        }

        return -1;
    }

    /**
     * Method return free up index on kernel
     * @param upRingIndex index of up ring
     * @return free up index on kernel
     */
    private int getFreeKernelIndex(int upRingIndex){
        //kernel is empty
        if (upRingIndex == -1){
            return field[0].length - 1;
        }

        return upRingIndex - 1;
    }

    /**
     * Method return up ring index
     * @param kernel index for which you want to find the position of the upper ring
     * @return index of up ring. -1 if kernel is empty
     */
    private int getUpRingIndex(int kernel){
        for (int i = 0; i < field[0].length; i++) {
            if (field[kernel][i] != 0){
                return i;
            }
        }
        //Kernel is empty
        return - 1;
    }

    /**
     * Method check if it is possible to execute a given move.
     * If move is impossible print to console reason.
     * @param fromKernel source kernel from which is need to move the ring
     * @param toKernel destination kernel to which is need to move the ring
     * @param sourceRingIndex index of the ring to be moved
     * @param destinationRingIndex index of the ring on the kernel to which we will move
     * @return true if move is possible, false if move is impossible
     */
    private boolean isMovePossible (int fromKernel, int toKernel, int sourceRingIndex, int destinationRingIndex){
        if (fromKernel > field.length || toKernel > field.length){
            System.out.println("Неверно задан номер стержня");
            return false;
        }

        if (sourceRingIndex == -1){
            System.out.println("Исходный стержень пуст");
            return false;
        }

        if (destinationRingIndex == 0){
            System.out.println("Стержень заполнен");
            return false;
        }

        if (fromKernel == toKernel){
            System.out.println("Стержни совпадают");
            return false;
        }


        //destination kernel is empty
        if (destinationRingIndex == -1){
            return true;
        }

        if (field[fromKernel][sourceRingIndex] > field[toKernel][destinationRingIndex] && field[toKernel][destinationRingIndex] != 0){
            System.out.println(String.format("Ошибка. Нельзя переместить с %d в %d", fromKernel + 1, toKernel + 1));
            return false;
        }

        return true;
    }

    /**
     * Method checks game over
     * @return true if game over, false game is not over
     */
    private boolean isGameOver (){
        if (field[2][0] == 1 || field[1][0] == 1){
            return true;
        }
        return false;
    }
}
