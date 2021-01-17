package HomeWork5.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class MapValueComparator<K, V> implements Comparator<V>{
        Map<K, V> map;

        public MapValueComparator(Map<K, V> map) {
            this.map = map;
        }

        @Override
        public int compare(Object keyA, Object keyB) {
            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);

            //Если value равны, то сравниваем key
            int valueComparison = valueB.compareTo(valueA);
            return valueComparison == 0 ?  ((Comparable) keyB).compareTo(keyA) : valueComparison;
        }
}
