package HomeWork4;

import HomeWork4.dto.DataContainer;
import HomeWork4.dto.person.Person;
import HomeWork4.dto.person.PersonComparator;

import java.util.Comparator;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork4Main {
    public static void main(String[] args) {

        /*
         * Контейнер объектов типа Number
         */
        System.out.println("====== Контейнер чисел ======");

        final int itemCounts = 10;
        DataContainer<Number> containerNumbers = new DataContainer<>(new Number[itemCounts]);

        //Добавление объектов в контейнер
        for (int i = itemCounts; i > 0; i--) {
            containerNumbers.add(i);
        }

        Float additionalNumber = -1233.56f;
        Comparator<Number> numberComparator = new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                if (o1 == null || o2 == null) {
                    return -2;
                }
                return (o1.doubleValue() < o2.doubleValue()) ? -1 : (o1.doubleValue() > o2.doubleValue()) ? 1 : 0;
            }
        };
        testDataContainer(containerNumbers, additionalNumber, numberComparator);

        /*
        * Контейнер объектов типа Person
        */
        System.out.println("\n\n\n====== Контейнер объектов типа Person ======");
        final int objectCounts = 5;
        DataContainer<Person> container = new DataContainer<>(new Person[objectCounts]);

        //Добавление объектов в контейнер
        for (int i = objectCounts; i > 0; i--) {
            container.add(new Person("Name" + i, i));
        }

        System.out.println(container.delete(objectCounts - 1));
        System.out.println(container.toString());

        Person additionalPerson = new Person("Name155", 4);
        testDataContainer(container, additionalPerson, new PersonComparator());
    }

    /**
     * Данные метод тестирует все методы класса DataContainer
     * @param container контейнер
     * @param additionalItem дополнительный элемент добавляемый в контейнер
     * @param comparator компаратор для сравнения элементов контейнера
     * @param <T> тип данных контейнера
     */
    public static <T> void testDataContainer(DataContainer<T> container, T additionalItem, Comparator<T> comparator){
        System.out.println("Исходный контейнер: " + container.toString());

        System.out.println("\nРезультат удаления элемента с индексом 1: " + container.delete(1));
        System.out.println("Результат удаления элемента с индексом 1: " + container.delete(1));
        System.out.println("Результат удаления элемента с индексом 155: " + container.delete(155));
        System.out.println("Контейнер после удаления по индексу: " + container.toString());

        System.out.println("Объект с индексом 1: " + container.get(1));
        System.out.println("Объект с индексом 202: " + container.get(202));

        System.out.println("\nРезультат удаления объекта " + additionalItem + ": " + container.delete(additionalItem));
        System.out.println("Результат удаления объекта " + container.get(3) + ": " + container.delete(container.get(3)));
        System.out.println("Контейнер после удаления по объекту: " + container.toString());

        System.out.println("\nРезультат добавления объекта " + additionalItem + ": " + container.add(additionalItem));
        System.out.println("Результат добавления объекта " + additionalItem + ": " + container.add(additionalItem));
        System.out.println("Результат добавления объекта " + additionalItem + ": " + container.add(additionalItem));
        System.out.println("Контейнер после добавления объектов: " + container.toString());

        System.out.println("\nРезультат удаления объекта " + additionalItem + ": " + container.delete(additionalItem));
        System.out.println("Контейнер после удаления по объекту " + additionalItem + ": " + container.toString());

        System.out.println("\nРезультат getItems: " + new DataContainer<T>(container.getItems()).toString());

        System.out.println("\nРезультат добавления объекта " + additionalItem + ": " + container.add(additionalItem));
        System.out.println("Контейнер после добавления объекта: " + container.toString());
        container.sort(comparator);
        System.out.println("Результат сортировки:" + container.toString());
    }
}
