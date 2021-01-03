package HomeWork4;

import HomeWork4.dto.DataContainer;
import HomeWork4.dto.person.Person;
import java.util.Random;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork4Task11Main {
    public static void main(String[] args) {
        final int personCount = 12;
        DataContainer<Person> container = new DataContainer<>(new Person[personCount]);
        Random random = new Random();

        //Добавление объектов в контейнер
        for (int i = 0; i < personCount; i++) {
            int x = random.nextInt(20);
            container.add(new Person("Name" + x, x));
        }

        System.out.println("Исходный контейнер: " + container.toString());
        DataContainer.sort(container);
        System.out.println("\nРезультат сортировки через Comparable:" + container.toString());
    }
}
