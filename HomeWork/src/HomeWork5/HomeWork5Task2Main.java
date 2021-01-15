package HomeWork5;

import HomeWork5.utils.MapValueComparator;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork5Task2Main {
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

        String text = textFromFile.replaceAll("[-+*:.,!?()\\|\\[\\]\\n\\r]", " ").trim().replaceAll("\\s+", " ");
        if (text.length() == 0) {
            System.out.println("Файл не содержит слов!");
            System.out.println("Содержимое файла: \n");
            System.out.println(textFromFile);
            return;
        }

        //Помещаем все слова из текста в List
        List<String> wordsContainer = new ArrayList<>(Arrays.asList(text.split(" ")));

        /*
            2.1 Найти в тексте все уникальные слова и поместить их в коллекцию Set.
         */
        Set<String> uniqueWords = new HashSet<>(wordsContainer);
        System.out.println("Слов в тексте:" + wordsContainer.size());
        System.out.println("Количество уникальных слов: " + uniqueWords.size());


        /*
            2.2. Найти в тексте топ 10 слов и вывести количество этих слов используя Map.
         */
        Map<String, Long> countEachWord = new HashMap<>();

        //Подсчет сколько раз встречается каждое уникальное слово
        for (String word : wordsContainer) {
            if (countEachWord.containsKey(word)){
                long count = countEachWord.get(word);
                countEachWord.put(word, ++count);
            } else {
                countEachWord.put(word, 1L);
            }
        }

        //Сортировка по значению
        Map<String, Long> countEachWordSortByCount = sortByValue(countEachWord);

        System.out.println("\nТоп 10 слов:");
        int i = 0;
        for (String key : countEachWordSortByCount.keySet()) {
            if (i++ < 10) {
                System.out.printf("%d. %s - %d раз%n", i, key, countEachWord.get(key));
            } else {
                break;
            }
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
            throw new FileNotFoundException("Файл не найден. Путь к файлу:\" + textFilePath");
        }
    }

    /**
     * Данный метод выполняет чтение текста из текстового файла
     * @param textFilePath Полный путь к текстовому файлу
     * @return текстовую строку прочитанную из файла. При чтении символы перевода строки не добавляются в строку
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
                textFileData.append(" ");
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
}
