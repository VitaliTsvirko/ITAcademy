package HomeWork5.utils;

import HomeWork5.dto.ISearchEngine;

/**
 * Created by Vitali Tsvirko
 */
public class SearchEngineNormalizer implements ISearchEngine {
    ISearchEngine searcher;

    public SearchEngineNormalizer(ISearchEngine searcher) {
        this.searcher = searcher;
    }

    @Override
    public long search(String text, String word) {
        if (text == null) {
            throw new NullPointerException("Text is null");
        }
        if (word == null) {
            throw new NullPointerException("Word is null");
        }

        return searcher.search(stringNormalizer(text), word);
    }

    /**
     * Данный метод удаляет из полученной строки специальные символы и двойные пробелы
     * @param text исходный текст
     * @return текстовая строка без специальных символов и двойных пробелов
     */
    private String stringNormalizer(String text){
        return text.replaceAll("([+*:.,!?();=<>\\n\\r])|(-{2,})", " ")     //Убираем символы + * : . , ! ? ( ) ; = < > -- и строки
                .replaceAll("[\"'|\\[\\]]", " ")                           //Убираем символы " ' | [ ]
                .replaceAll("(\\s-)|(^-)", " ")                            //Убираем символы -
                .trim()                                                                    //Убираем пробелы в начале и конце строки
                .replaceAll("\\s{2,}", " ");                               //Убираем двойные символы
    }
}
