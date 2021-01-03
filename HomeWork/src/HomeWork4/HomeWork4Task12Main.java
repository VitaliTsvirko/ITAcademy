package HomeWork4;

import HomeWork4.dto.DataContainer;
import HomeWork4.dto.person.Person;
import HomeWork4.dto.person.PersonComparator;

import java.util.Random;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork4Task12Main {
    public static void main(String[] args) {
        DataContainer container = new DataContainer(12);
        Random random = new Random();

        //Добавление объектов в контейнер
        for (int i = 0; i < 12; i++) {
            int x = random.nextInt(20);
            container.add(new Person("Name" + x, x));
        }

        System.out.println("Исходный контейнер: " + container.toString());
        DataContainer.sort(container, new PersonComparator());
        System.out.println("\nРезультат сортировки:" + container.toString());
    }
}
