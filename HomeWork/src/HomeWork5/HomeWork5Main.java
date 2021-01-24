package HomeWork5;

import HomeWork5.dto.ISearchEngine;
import HomeWork5.utils.PathToFileBuilder;
import HomeWork5.dto.TextWordsStatistic;
import HomeWork5.utils.TextReader;
import HomeWork5.utils.*;

import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class HomeWork5Main {
    public static void main(String[] args) {
        String textFromFile;
        String fileName = "Война и мир_книга.txt";

        /* Для тестирования:*/
       //fileName = "test.txt";
       //fileName = "ANCI.txt";
       //fileName = "symbols.txt";
       //fileName = "empty.txt";
       //fileName = "NoExistFile.txt";


        /*
            Чтение текста из файла
         */
        try{
            String textFilePath = PathToFileBuilder.buildPathToFileInCurrentProject(fileName, "HomeWork", "src", "HomeWork5", "resources");
            textFromFile = TextReader.readTextFromFile(textFilePath);
        }catch (NullPointerException e){
            System.out.println("Ошибка!");
            return;
        }catch (Exception e){
            System.out.println("Ошибка чтения файла. " + e.getMessage());
            return;
        }

        /*
            Работа с текстом
         */
        TextWordsStatistic textWordsStatistic;
        try{
            textWordsStatistic = new TextWordsStatistic(textFromFile);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }


        /*
            2.1 Найти в тексте все уникальные слова и поместить их в коллекцию Set.
         */
        Set<String> uniqueWords = textWordsStatistic.getUniqueWordsContainer();
        System.out.println("Слов в тексте:" + textWordsStatistic.getWordsNumber());
        System.out.println("Количество уникальных слов: " + uniqueWords.size());

        /*
            2.2. Найти в тексте топ 10 слов и вывести количество этих слов используя Map.
         */
        int topWordsToPrintNumber = 10;

        Map<String , Long> topWords  = textWordsStatistic.getTopPopularWords(topWordsToPrintNumber);

        System.out.printf("\nТоп %d популярных слов:\n", topWordsToPrintNumber);
        System.out.println(textWordsStatistic.topWordMapToStringFormat(topWords));

        //Самые редкие слова
        topWords  = textWordsStatistic.getTopRarestWords(topWordsToPrintNumber);

        System.out.printf("\nТоп %d самых редких слов:\n", topWordsToPrintNumber);
        System.out.println(textWordsStatistic.topWordMapToStringFormat(topWords));

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
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }

        /*
           4.2* Написать класс RegExSearch реализующий интерфейс ISearchEngine.
           Реализовать поиск при помощи класса Matcher.
         */

        RegExSearch regExSearch = new RegExSearch();
        System.out.println("\nПоиск с использованием RegExSearch (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = regExSearch.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }

        /*
        4.3* Написать декоратор SearchEngineNormalizer для ISearchEngine который будет удалять нежелательные символы.
                Любые знаки препинания, лишние пробелы и например переводы строк.
        */

        ISearchEngine easySearchWithStringNormalize = new SearchEngineNormalizer(new EasySearch());
        System.out.println("\nПоиск с использованием SearchEngineNormalizer(new EasySearch()) (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = easySearchWithStringNormalize.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }


        ISearchEngine regExSearchWithStringNormalize = new SearchEngineNormalizer(new RegExSearch());
        System.out.println("\nПоиск с использованием SearchEngineNormalizer(new RegExSearch()) (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = regExSearchWithStringNormalize.search(textLowerCase, searchWord.toLowerCase());
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }


        /*
            4.4* Написать декоратор для ISearchEngine который будет позволять искать данные без учёта регистра.
         */

        RegExSearchNormalizer RegExSearchNormalizer1 = new RegExSearchNormalizer(new EasySearch());
        RegExSearchNormalizer1.setCaseInsensitive(false);

        System.out.println("\nПоиск с использованием RegExSearchNormalizer(new EasySearch()) (c учетом регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = RegExSearchNormalizer1.search(textFromFile, searchWord);
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }


        RegExSearchNormalizer RegExSearchNormalizer2 = new RegExSearchNormalizer(new RegExSearch());
        RegExSearchNormalizer2.setCaseInsensitive(true);

        System.out.println("\nПоиск с использованием RegExSearchNormalizer(new RegExSearch()) (без учета регистра)");
        for (String searchWord : searchWords) {
            long searchWordNumber = RegExSearchNormalizer2.search(textFromFile, searchWord);
            System.out.printf("\tСлово \"%s\" встречается %d раз%n", searchWord, searchWordNumber);
        }

    }

}
