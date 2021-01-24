package HomeWork5.dto;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class MapValueComparator<K, V> implements Comparator<V>{
        Map<K, V> map;
        final boolean descendingSort;

        public MapValueComparator(Map<K, V> map, boolean descendingSort) {
            this.map = map;
            this.descendingSort = descendingSort;
        }

        @Override
        public int compare(Object keyA, Object keyB) {
            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);

            if (this.descendingSort){
                //Если value равны, то сравниваем key
                int valueComparison = valueA.compareTo(valueB);
                return valueComparison == 0 ?  ((Comparable) keyA).compareTo(keyB) : valueComparison;
            } else {
                //Если value равны, то сравниваем key
                int valueComparison = valueB.compareTo(valueA);
                return valueComparison == 0 ?  ((Comparable) keyB).compareTo(keyA) : valueComparison;
            }
        }
}
