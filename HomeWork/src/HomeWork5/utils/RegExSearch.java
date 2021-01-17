package HomeWork5.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vitali Tsvirko
 */
public class RegExSearch implements ISearchEngine{

    /**
     * Метод возвращает информации о количестве раз которое встречается слово в тексте.
     * @param text текст в котором ищем
     * @param word текст который ищем в тексте
     * @return число раз которое встречается слово word в тексте text
     */
    @Override
    public long search(String text, String word) {
        long count = 0;
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            count++;
        }

        return count;
    }
}
