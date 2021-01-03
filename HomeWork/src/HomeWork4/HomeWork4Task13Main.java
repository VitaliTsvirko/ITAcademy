package HomeWork4;

import HomeWork4.dto.DataContainer;
import HomeWork4.dto.person.Person;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork4Task13Main {
    public static void main(String[] args) {
        final int personCount = 12;
        DataContainer<Person> container = new DataContainer<>(new Person[personCount]);
        Random random = new Random();

        //Добавление объектов в контейнер
        for (int i = 0; i < personCount; i++) {
            int x = random.nextInt(30);
            container.add(new Person("Name" + x, x));
        }

        System.out.println("Исходный контейнер: ");
        for (Person item : container.getItems()){
            System.out.println(item);
        }


        System.out.println("\nИмена людей с возрастом больше 18: ");
        for (Iterator<Person> iterator = container.iterator(); iterator.hasNext();){
            Person person = iterator.next();
            if (person.getAge() > 18) {
                System.out.println(person.getName());
                iterator.remove();
            }
        }

        System.out.println("\nКонтейнер после удаления людей возраст которых больше 18: \n" + container.toString());
    }
}
