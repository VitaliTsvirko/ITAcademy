
package additional.hanoi.odt;

/**
 * Created by Vitali Tsvirko
 */
public class RecordedGame extends Player{
    private String[] moveCommands;
    private int commandIndex;

    public RecordedGame(String[] moveCommands){
        this.moveCommands = moveCommands;
    }

    public int[] requestMove (){
        if (commandIndex >= moveCommands.length){
            return null;
        }
        else {
            return parseCommand(moveCommands[commandIndex++]);
        }
    }
}
