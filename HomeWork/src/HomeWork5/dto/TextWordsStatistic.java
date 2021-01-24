package HomeWork5.dto;

import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class TextWordsStatistic {
    private String text;
    private List<String> wordsData;
    private Set<String> uniqueWordsData;

    /**
     * Default constructor
     */
    public TextWordsStatistic() {

    }


    /**
     * Конструктор принимающий текстовую строку
     * При инициализации объекта происходит разбивка текста на слова (без знаков препинания) и уникальные слова,
     * которые хранятся в полях {@code wordsData} и {@code uniqueWordsData} и могут быть получены через геттеры
     * {@code getWordsContainer} {@code getUniqueWordsContainer}
     * @param text текстовая строка для обработки
     * @throws IllegalArgumentException если переданный текст не содержит слов
     */
    public TextWordsStatistic(String text) throws IllegalArgumentException{
        this.text = text;
        dataWrite ();
    }


    /**
     * Данный метод задает значение текстового поля {@code text}
     * После вызова данного метода происходит разбивка текста на слова (без знаков препинания) и уникальные слова,
     * которые хранятся в полях {@code wordsData} и {@code uniqueWordsData} и могут быть получены через геттеры
     * {@code getWordsContainer} {@code getUniqueWordsContainer}
     * @param text текстовая строка для обработки
     * @throws IllegalArgumentException если переданный текст не содержит слов
     */
    public void setText(String text) throws IllegalArgumentException{
        this.text = text;
        dataWrite ();
    }


    /**
     * Данные метод возвращает текстовую строку содержащую исходный обрабатываемый текст
     * @return текстовую строку содержащую исходный обрабатываемый текст
     */
    public String getText() {
        return text;
    }


    /**
     * Данные метод возвращает {@code List} содержащий все слова текста {@code text}
     * @return возвращает {@code List} содержащий все слова обрабатываемого текста {@code text}
     */
    public List<String> getWordsContainer() {
        return wordsData;
    }


    /**
     * Данные метод возвращает общее количество слов в тексте {@code text}
     * @return общее количество слов в тексте
     */
    public int getWordsNumber (){
        return wordsData.size();
    }


    /**
     * Данные метод возвращает {@code Set} содержащий все уникальные слова текста {@code text}
     * @return {@code Set} содержащий все уникальные слова текста {@code text}
     */
    public Set<String> getUniqueWordsContainer (){
        return this.uniqueWordsData;
    }


    /**
     * Данные метод возвращает общее количество уникальных слов в тексте {@code text}
     * @return общее количество уникальных слов в тексте
     */
    public int getUniqueWordsNumber (){
        return uniqueWordsData.size();
    }


    /**
     * Данный метод возвращает текстовую строку без знаков препинания, множественные пробелы на одиночные
     * и символов перевода строки и каретки
     * К знакам препинания относиться: + * : . , ! ? ( ) ; " ' | --
     * @param text текстовая строка
     * @return возвращает текстовую строку без знаков препинания, множественные пробелы на одиночные
     *         и символов перевода строки и каретки
     * @throws NullPointerException если {@code text} равен {@code null}
     */
    public String getTextNoPunctuation (String text) {
        if (text == null){
            throw new NullPointerException("Ошибка в тексте!");
        }

        return text.replaceAll("([+*:.,!?();=<>\\n\\r])|(-{2,})", " ")     //Убираем символы + * : . , ! ? ( ) ; = < > -- и строки
                    .replaceAll("[\"'|\\[\\]]", " ")                       //Убираем символы " ' | [ ]
                    .replaceAll("(\\s-)|(^-)", " ")                        //Убираем символы -
                    .trim()                                                                //Убираем пробелы в начале и конце строки
                    .replaceAll("\\s{2,}", " ");                           //Убираем двойные символы
    }


    /**
     * Данный метод возвращает {@Map} самых популярных слов в тексте. Количество слов задается параметром {@param wordNumber}
     * Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @param wordNumber количество слов, которое необходимо передать
     * @return {@Map} самых популярных слов в тексте в количестве {@param wordNumber}.
     *                Если {@param wordNumber} превышает количество слов то будет возвращен {@Map} всех имеющихся слов
     * @throws IllegalArgumentException если {@param wordNumber} отрицательный.
     */
    public Map<String , Long> getTopPopularWords (long wordNumber){
        if (wordNumber < 0){
            throw new IllegalArgumentException("Количество слов не может быть отрицательным");
        }

        return numberEachWordSortedByNumber(wordNumber, false);
    }


    /**
     * Данный метод возвращает {@Map} самых редких слов в тексте. Количество слов задается параметром {@param wordNumber}
     * Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @param wordNumber количество слов, которое необходимо передать
     * @return {@Map} самых редких слов в тексте в количестве {@param wordNumber}.
     *                Если {@param wordNumber} превышает количество слов то будет возвращен {@Map} всех имеющихся слов
     * @throws IllegalArgumentException если {@param wordNumber} отрицательный.
     */
    public Map<String , Long> getTopRarestWords (long wordNumber){
        if (wordNumber < 0){
            throw new IllegalArgumentException("Количество слов не может быть отрицательным");
        }

        return numberEachWordSortedByNumber(wordNumber, true);
    }


    /**
     * Данный метод преобразует {@Map} в текстовую строку вида:
     * 1. Слово - Сколько раз оно встречается в тексте
     * @param map {@Map} содержащая Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @return текстовую строку
     * @throws IllegalArgumentException если {@param map}  равен {@code null}.
     */
    public String topWordMapToStringFormat(Map<String , Long> map){
        if (map == null){
            throw new IllegalArgumentException("Неверный аргумент");
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (String key : map.keySet()) {
            stringBuilder.append(String.format("%d. \"%s\" - %d раз%n", ++i, key, map.get(key)));
        }

        return stringBuilder.toString();
    }


    /**
     * Данные метод возвращает сколько раз солово {@param word} встречается тексте {@code text}
     * @param word слово
     * @return сколько раз солово {@param word} встречается тексте {@code text}
     *         Если искомое слово отсутствует в тексте вернет {@code null}
     */
    public Long getNumberOfWord (String word){
        Map<String, Long> counter = countNumberOfEachWord();
        return counter.get(word);
    }


    /**
     * Данные метод считает сколько раз каждое солово встречается в переданном списке
     * @return {@Map} содержащую Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     */
    private Map<String , Long> countNumberOfEachWord(){
        Map<String, Long> counter = new HashMap<>();

        //Счет, сколько раз встречается каждое уникальное слово
        for (String word : this.wordsData) {
            if (counter.containsKey(word)){
                long count = counter.get(word);
                counter.put(word, ++count);
            } else {
                counter.put(word, 1L);
            }
        }

        return counter;
    }


    /**
     * Данный метод заполняет поля {@code wordsData} и {@code uniqueWordsData} после обновления поля {@code text}
     */
    private void dataWrite () {
        String textNoPunctuation = getTextNoPunctuation(this.text);
        if (textNoPunctuation.equals("")){
            throw new IllegalArgumentException("Текст не содержит слов");
        }

        this.wordsData = new ArrayList<>(Arrays.asList(textNoPunctuation.split(" ")));
        this.uniqueWordsData = new HashSet<>(this.wordsData);
    }


    /**
     * Данный метод возвращает {@Map} самых редких или самых популярных слов в тексте.
     * Количество слов задается параметром {@param wordNumber}
     * Тип Редкие или Популярные слова задается {@param descendingSort}
     * {@Map} Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @param wordNumber количество слов, которое необходимо передать
     * @param descendingSort если false то возвращается {@Map} популярных слов,
     *                       если true то возвращается {@Map} редких слов,
     * @return {@Map} самых редких или популярных слов в тексте в количестве {@param wordNumber}.
     *                Если {@param wordNumber} превышает количество слов то будет возвращен {@Map} всех имеющихся слов
     */
    private Map<String , Long> numberEachWordSortedByNumber (long wordNumber, boolean descendingSort){
        Map<String , Long> numberOfEachWord = countNumberOfEachWord();
        //Сортировка по значению
        Map<String, Long> numberEachWordSortedByNumber = sortByValue(numberOfEachWord, descendingSort);
        Map<String , Long> topWords = new LinkedHashMap<>();

        int i = 0;
        for (String key : numberEachWordSortedByNumber.keySet()) {
            if (i++ < wordNumber) {
                topWords.put(key, numberOfEachWord.get(key));
            } else {
                break;
            }
        }

        return topWords;
    }

    /**
     * Данные метод выполняет сортировку Map, по значению
     * Для сортировки используется компаратор {@code MapValueComparator}
     * @param unsortedMap неотсортированная Map. Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @return отсортированный по убыванию значений Map
     */
    private Map<String, Long> sortByValue(Map<String, Long> unsortedMap, boolean descendingSort) {
        Map<String, Long> sortedMap = new TreeMap<String , Long>(new MapValueComparator(unsortedMap, descendingSort));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
}
