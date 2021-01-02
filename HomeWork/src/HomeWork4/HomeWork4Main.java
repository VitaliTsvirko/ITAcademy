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
        //Контейнер примитивных типов
        System.out.println("====== Контейнер примитивных типов ======");

        final int itemCounts = 10;
        DataContainer containerNative = new DataContainer(itemCounts + 10);

        for (long i = itemCounts; i > 0; i--) {
            containerNative.add(i);
        }
        System.out.println("Исходный контейнер: " + containerNative.toString());

        System.out.println("\nРезультат удаления элемента с индексом 1: " + containerNative.delete(1));
        System.out.println("Результат удаления элемента с индексом 1: " + containerNative.delete(1));
        System.out.println("Результат удаления элемента с индексом 155: " + containerNative.delete(155));
        System.out.println("Контейнер после удаления по индексу: " + containerNative.toString());

        System.out.println("\nЗначение элемента с индексом 1: " + containerNative.get(1));
        System.out.println("Значение элемента с индексом 155: " + containerNative.get(155));

        long newItem = -156;
        System.out.println("\nРезультат добавления элемента " + newItem + ": " + containerNative.add(newItem));
        System.out.println("Контейнер после добавления элемента: " + containerNative.toString());
        System.out.println("Результат удаления элемента " + newItem + ": " + containerNative.delete(newItem));
        System.out.println("Контейнер после удаления по элементу: " + containerNative.toString());

        System.out.println("\nРезультат добавления элемента " + newItem + ": " + containerNative.add(newItem));
        System.out.println("Результат добавления элемента " + newItem + ": " + containerNative.add(newItem));
        System.out.println("Контейнер после добавления элементов: " + containerNative.toString());

        System.out.println("\nРезультат удаления элементов " + newItem + ": " + containerNative.delete(newItem));
        System.out.println("Контейнер после удаления по элементу " + newItem + ": " + containerNative.toString());

        System.out.println("\nРезультат добавления элемента " + newItem + ": " + containerNative.add(newItem));
        System.out.println("Контейнер после добавления элемента: " + containerNative.toString());
        containerNative.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 == null || o2 == null){
                    return -2;
                }

                if (o1.longValue() > o2.longValue()){
                    return 1;
                }
                if (o1.longValue() < o2.longValue()){
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("Результат сортировки:" + containerNative.toString());


        //Контейнер объектов типа Person
        System.out.println("\n\n\n====== Контейнер объектов типа Person ======");
        final int objectCounts = 5;
        DataContainer container = new DataContainer(objectCounts);

        //Добавление объектов в контейнер
        for (int i = objectCounts; i > 0; i--) {
            container.add(new Person("Name" + i, i));
        }

        System.out.println("Исходный контейнер: " + container.toString());

        System.out.println("\nРезультат удаления элемента с индексом 1: " + container.delete(1));
        System.out.println("Результат удаления элемента с индексом 1: " + container.delete(1));
        System.out.println("Результат удаления элемента с индексом 155: " + container.delete(155));
        System.out.println("Контейнер после удаления по индексу: " + container.toString());

        System.out.println("Объект с индексом 1: " + container.get(1));
        System.out.println("Объект с индексом 202: " + container.get(202));

        Person newPerson = new Person("Name155", 4);
        System.out.println("\nРезультат удаления объекта " + newPerson + ": " + container.delete(newPerson));
        System.out.println("Результат удаления объекта " + container.get(3) + ": " + container.delete(container.get(3)));
        System.out.println("Контейнер после удаления по объекту: " + container.toString());

        System.out.println("\nРезультат добавления объекта " + newPerson + ": " + container.add(newPerson));
        System.out.println("Результат добавления объекта " + newPerson + ": " + container.add(newPerson));
        System.out.println("Результат добавления объекта " + newPerson + ": " + container.add(newPerson));
        System.out.println("Контейнер после добавления объектов: " + container.toString());

        System.out.println("\nРезультат удаления объекта " + newPerson + ": " + container.delete(newPerson));
        System.out.println("Контейнер после удаления по объекту " + newPerson + ": " + container.toString());

        System.out.println("\nРезультат добавления объекта " + newPerson + ": " + container.add(newPerson));
        System.out.println("Контейнер после добавления объекта: " + container.toString());
        container.sort(new PersonComparator());
        System.out.println("Результат сортировки:" + container.toString());
    }
}
