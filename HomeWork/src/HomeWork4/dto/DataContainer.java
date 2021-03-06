package HomeWork4.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/**
 * Данный класс реализует контейнер для хранения, добавления, удаления и сортировки данных обобщённого типа
 * Created by Vitali Tsvirko
 */
public class DataContainer<T> implements Iterable<T>{
    private T[] data;

    public DataContainer(T[] data){
        this.data = data;
    }

    /**
     * Данный метод добавляет объекты обобщённого типа в контейнер
     * @param item - данные обобщённого типа
     * @return индекс объекта в контейнере. Вернет -1 если объект нельзя поместить в контейнер
     */
    public int add(T item){
        if (item == null){
            return -1;
        }
        int freeIndex = findFreeIndex();

        this.data[freeIndex] = item;
        return freeIndex;
    }

    /**
     * Данный метод возвращает объект из контейнера по его индексу
     * @param index - индекс объекта, который необходимо вернуть
     * @return объект с индексом index. В случае ошибки вернет null
     */
    public T get(int index){
        if (isIndexNotValid(index)){
            return null;
        }
        return this.data[index];
    }

    /**
     * Данный метод возвращает массив объектов контейнера
     * @return возвращает массив объектов контейнера
     */
    public T[] getItems(){
        return this.data;
    }

    /**
     * Данный метод удаляет объект из контейнера по его индексу
     * @param index индекс элемента который необходимо удалить
     * @return true если объект удален, false если объект нельзя удалить
     */
    public boolean delete(int index){
        if (isIndexNotValid(index)){
            return false;
        }

        //Удаление последнего элемента контейнера
        if (index == this.data.length - 1){
            newDataLength(this.data.length - 1);
            return true;
        }

        //Смещение всех элементов контейнера начиная от index на одну позицию влево
        for (int i = index; i < this.data.length - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        newDataLength(this.data.length - 1);
        return true;
    }

    /**
     * Данный метод удаляет все объекты из контейнера по значению (содержанию) объекта
     * @param item объект, который необходимо удалить
     * @return true если объект удален, false если объект нельзя удалить
     */
    public boolean delete(T item){
        int itemIndex = findItem(item);

        //Элемент не найден
        if (itemIndex == -1){
            return false;
        }

        //Удаление всех элементов равных item
        do{
            delete(itemIndex);
            itemIndex = findItem(item);
        }while (itemIndex != -1);

        return true;
    }

    /**
     * Данный метод выполняет сортировку контейнера
     * @param comparator объект который выполняет сравнение элементов контейнера
     */
    public void sort(Comparator<T> comparator){
        //Arrays.sort(this.data, comparator);
        sorter(this.getItems(), comparator);
    }

    /**
     * Данный метод выполняет сортировку контейнера c использованием compareTo объекта контейнера
     * @param container контейнер объектов
     */
    public static <T> void sort(DataContainer<? extends Comparable<T>> container){
        //Arrays.sort(container.data);
        sorter(container.getItems(), null);
    }

    /**
     * Данный метод выполняет сортировку переданного контейнера c использованием переданного объекта который выполняет
     * сравнивание объектов (Comparator)
     * @param container контейнер объектов
     * @param comparator компаратор для объектов контейнера
     * @param <T> тип данных контейнера
     */
    public static <T> void sort(DataContainer<T> container, Comparator<T> comparator){
        //Arrays.sort(container.data, comparator);
        sorter(container.getItems(), comparator);
    }

    /**
     * Данный метод возвращает строковое представление контейнера
     * @return строковое представление контейнера. null исключаются из строкового представления
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        boolean needDelimeter = false;
        for (T item : this.data) {

            if (item == null){
                continue;
            }

            if (needDelimeter) {
                stringBuilder.append(", ");
            }
            else {
                needDelimeter = true;
            }

            stringBuilder.append(item.toString());
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /**
     * Данные метод возвращает итератор для контейнера
     * @return итератор для контейнера
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }


    /**
     * Данный метод выполняет сортировку используя интерфейсы Сomparator или Comparable
     * @param array массив для сортировки
     * @param comparator компаратор для объектов контейнера. Если равен null, то используется compareTo
     * @param <T> тип данных контейнера
     */
    private static <T> void sorter(T[] array, Comparator<T> comparator){
        boolean isSwap;
        boolean needSwap;

        do {
            isSwap = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (comparator == null){
                    Comparable o1 = (Comparable) array[i];
                    Comparable o2 = (Comparable) array[i + 1];
                    needSwap =  (o1.compareTo(o2) > 0);
                } else {
                    needSwap = (comparator.compare(array[i], array[i+1]) > 0);
                }

                if (needSwap){
                    swap(array, i, i + 1);
                    isSwap = true;
                }
            }
        }while (isSwap);
    }

    /**
     * Данные метод меняет местами элементы массива
     * @param array массив
     * @param from индекс первого элемента
     * @param to индекс второго элемента
     * @param <T> тип элементов массива
     */
    private static <T> void swap(T[] array, int from, int to){
        T tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    /**
     * Данный метод проверяет валидность индекса
     * @param index индекс контейнера
     * @return true если индекс не верный, false если индекс верный
     */
    private boolean isIndexNotValid(int index){
        return (index >= this.data.length || index < 0);
    }

    /**
     * Данные метод возвращает свободный (незанятый) индекс в контейнере.
     * Если свободных элементов нет, то расширяет массив
     * @return свободный (незанятый) индекс в контейнере.
     */
    private int findFreeIndex(){
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                return i;
            }
        }
        newDataLength(this.data.length + 1);
        return this.data.length - 1;
    }

    /**
     * Данный метод изменяет длину массив data до length
     * @param length новая длина массива
     */
    private void newDataLength (int length){
        this.data = Arrays.copyOf(this.data, length);
    }

    /**
     * Данный метод возвращает индекс объекта item в контейнере
     * @param item объект индекс которого необходимо найти
     * @return индекс объекта item в контейнере. Вернет -1 если объект не найден
     */
    private int findItem(T item){
        for (int i = 0; i < this.data.length; i++) {
            if (Objects.equals(this.data[i], item)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Iterator for DataContainer;
     */
    private class Itr implements Iterator<T>{
        private int cursor;
        private int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return (this.cursor != data.length);
        }

        @Override
        public T next() {
            if (hasNext()){
                int i = this.cursor;
                ++this.cursor;

                return data[this.lastIndex = i];
            }
            return null;
        }

        @Override
        public void remove() {
            if (lastIndex > 0){
                this.cursor = lastIndex;
                delete(lastIndex);
            }
        }
    }

}

