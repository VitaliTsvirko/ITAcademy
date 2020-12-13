
package additional.hanoi.odt;

/**
 * Created by Vitali Tsvirko
 */
public class AutoPlayer extends Player{
    private int ringCount;
    private String[] moveCommands;
    private int commandIndex;

    public AutoPlayer(int ringCount){
        this.ringCount = ringCount;
        moveCommands = new String[(int) Math.pow(2,ringCount) - 1];

        autoMode(ringCount, 1,2,3);
        commandIndex = 0;

    }

    public int[] requestMove (){
        if (commandIndex >= moveCommands.length){
            return null;
        }
        else {
            return parseCommand(moveCommands[commandIndex++]);
        }
    }

    private void autoMode(int kernelCount, int sourceKernel, int destinationKernel, int bufferKernel){
        if (kernelCount == 1) {
            moveCommands[commandIndex++] = String.format("%d>%d", sourceKernel, destinationKernel);
        } else {
            autoMode(kernelCount - 1, sourceKernel, bufferKernel, destinationKernel);
            moveCommands[commandIndex++] = String.format("%d>%d", sourceKernel, destinationKernel);
            autoMode(kernelCount - 1, bufferKernel, destinationKernel, sourceKernel);
        }

    }

}
