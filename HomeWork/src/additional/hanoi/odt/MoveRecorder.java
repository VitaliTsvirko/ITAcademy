
package additional.hanoi.odt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class MoveRecorder {
    private static List<String> moveList = new ArrayList();

    public static void moveRecord(String move){
        moveList.add(move);
    }

    public String[] getMoveRecord(){
        return (String[]) moveList.toArray();
    }

    public static String[] getMoveRecord(int ringCount){
        switch (ringCount){
            case 3:
                return new String[]{"1>3", "1>2", "3>2", "1>3", "2>1", "2>3", "1>3"};
            default:
                return null;
        }
    }

}
