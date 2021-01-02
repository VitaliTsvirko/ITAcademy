package HomeWork4.dto.person;

import java.util.Comparator;

/**
 * Данный класс сравнивает объекты класса Person
 * Сравнение выполняться по полю age
 * Created by Vitali Tsvirko
 */
public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.age == o2.age){
            return 0;
        }
        if (o1.age > o2.age){
            return 1;
        }
            return -1;
    }
}
