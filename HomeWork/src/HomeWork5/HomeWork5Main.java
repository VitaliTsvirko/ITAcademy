package HomeWork5;

import HomeWork5.utils.*;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork5Main {
    public static void main(String[] args) {
        String textFromFile;
        String textFilePath;
        String fileName = "Война и мир_книга.txt";

        /* Для тестирования:*/
       //fileName = "test.txt";
        //fileName = "symbols.txt";
        //fileName = "symbols2.txt";
        //fileName = "empty.txt";

        try{
            textFilePath = getPathToTextFile(fileName);
            textFromFile = getTextFromFile(textFilePath);
        }catch (NullPointerException e){
            System.out.println("Ошибка!");
            return;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        //Получаем текст без знаков препинания
        String textNoPunctuation = textFromFile.replaceAll("([+*:.,!?();\"\'\\|\\[\\]\\n\\r])|(-{2,})", " ")
                                               .trim()
                                               .replaceAll("\\s{2,}", " ");

        if (textNoPunctuation.length() == 0) {
            System.out.println("Файл не содержит слов!");
            System.out.println("Содержимое файла: \n");
            System.out.println(textFromFile);
            return;
        }

        //Помещаем все слова из текста в List
        List<String> wordsContainer = new ArrayList<>(Arrays.asList(textNoPunctuation.split(" ")));

        /*
            2.1 Найти в тексте все уникальные слова и поместить их в коллекцию Set.
         */
        Set<String> uniqueWords = new HashSet<>(wordsContainer);
        System.out.println("Слов в тексте:" + wordsContainer.size());
        System.out.println("Количество уникальных слов: " + uniqueWords.size());


        /*
            2.2. Найти в тексте топ 10 слов и вывести количество этих слов используя Map.
         */
        //Map key - слово, value - сколько раз данное слово встречается в тексте
        Map<String , Long> numberOfEachWord = countNumberOfEachWord(wordsContainer);

        //Сортировка по значению
        Map<String, Long> numberEachWordSortedByNumber = sortByValue(numberOfEachWord);
        int topWordsToPrint = 10;

        System.out.printf("\nТоп %d слов:\n", topWordsToPrint);
        int i = 0;
        for (String key : numberEachWordSortedByNumber.keySet()) {
            if (i++ < topWordsToPrint) {
                System.out.printf("%d. \"%s\" - %d раз%n", i, key, numberOfEachWord.get(key));
            } else {
                break;
            }
        }

        //Самое редкое слово
        System.out.printf("\nСамое редкое слово - \"%s\", встречается %s раз \n",
                numberEachWordSortedByNumber.keySet().toArray()[numberEachWordSortedByNumber.size() - 1],
                numberEachWordSortedByNumber.values().toArray()[numberEachWordSortedByNumber.size() - 1].toString());



        /*
            Задания 4,5.
         */

        String textLowerCase = textFromFile.toLowerCase();
        String[] searchWords = {"Война", "и", "мир"};

        /*
            4.1 Написать класс EasySearch. Реализовать поиск используя метод indexOf из класса String.
	        В данной реализации запрещено использовать регулярные выражения в любом виде, для любых задач.
         */
        EasySearch easySearch = new EasySearch();
        System.out.println("\nПоиск с использованием easySearch (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = easySearch.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("Слово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }

        /*
           4.2* Написать класс RegExSearch реализующий интерфейс ISearchEngine.
           Реализовать поиск при помощи класса Matcher.
         */

        RegExSearch regExSearch = new RegExSearch();
        System.out.println("\nПоиск с использованием RegExSearch (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = regExSearch.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("Слово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }

        /*
        4.3* Написать декоратор SearchEngineNormalizer для ISearchEngine который будет удалять нежелательные символы.
                Любые знаки препинания, лишние пробелы и например переводы строк.
        */

        ISearchEngine easySearchWithStringNormalize = new SearchEngineNormalizer(new EasySearch());
        System.out.println("\nПоиск с использованием easySearchWithStringNormalize (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = easySearchWithStringNormalize.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("Слово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }


        ISearchEngine regExSearchWithStringNormalize = new SearchEngineNormalizer(new RegExSearch());
        System.out.println("\nПоиск с использованием RegExSearch (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = regExSearchWithStringNormalize.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("Слово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }
    }


    /**
     * Данный метод возвращает полный пусть к файлу fileName расположенному в папке resources текущего пакета
     * @param fileName имя файла
     * @return строку содержащую полный пусть к файлу fileName
     * @throws FileNotFoundException
     */
    private static String getPathToTextFile(String fileName) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String resourcePath = projectPath + "\\HomeWork\\src\\HomeWork5\\resources\\";

        String fullPath = resourcePath + fileName;
        File file = new File(fullPath);

        if (file.exists()){
            return fullPath;
        } else {
            throw new FileNotFoundException("Файл не найден. Путь к файлу:" + fullPath);
        }
    }

    /**
     * Данный метод выполняет чтение текста из текстового файла
     * @param textFilePath Полный путь к текстовому файлу
     * @return текстовую строку, содержащую данные прочитанные из файла.
     * @throws Exception
     */
    public static String getTextFromFile(String textFilePath) throws Exception {
        StringBuilder textFileData = new StringBuilder();
        BufferedReader bufferedReader = null;

        try{
            bufferedReader = new BufferedReader(new FileReader(textFilePath));
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("Файл не найден. Путь к файлу:" + textFilePath);
        }

        String textLine;

        try {
            textLine = bufferedReader.readLine();
            while(textLine != null){
                textFileData.append(textLine);
                textFileData.append("\n");
                textLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException | NullPointerException e){
            throw e;
        }

        if (textFileData.length() == 0){
            throw new FileSystemException("Файл пуст.");
        }
        else {
            return textFileData.toString();
        }
    }

    /**
     * Данные метод выполняет сортировку Map, по значению
     * @param unsortedMap неотсортированная Map. Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     * @return отсортированный по убыванию значений Map
     */
    public static Map<String, Long> sortByValue(Map<String, Long> unsortedMap) {
        Map<String, Long> sortedMap = new TreeMap<String , Long>(new MapValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }


    /**
     * Данные метод считает сколько раз каждое солово встречается в переданном списке
     * @param wordsContainer список слов
     * @return Ma, в котором Ключ - слово, Значение - сколько раз данное слово встречается в тексте
     */
    public static Map<String , Long> countNumberOfEachWord(List<String> wordsContainer){
        Map<String, Long> counter = new HashMap<>();

        //Подсчет сколько раз встречается каждое уникальное слово
        for (String word : wordsContainer) {
            if (counter.containsKey(word)){
                long count = counter.get(word);
                counter.put(word, ++count);
            } else {
                counter.put(word, 1L);
            }
        }

        return counter;
    }
}
