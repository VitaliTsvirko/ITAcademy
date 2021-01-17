package HomeWork5.utils;

public interface ISearchEngine {
    /**
     * Метод возвращает информации о количестве раз которое встречается слово в тексте.
     * @param text текст в котором ищем
     * @param word текст который ищем в тексте
     * @return число раз которое встречается слово word в тексте text
     */
    long search(String text, String word);
}
