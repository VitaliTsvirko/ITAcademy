package HomeWork5.utils;

import java.io.*;
import java.nio.file.FileSystemException;

/**
 * Created by Vitali Tsvirko
 */
public class TextReader {
    /**
     * Данный метод выполняет чтение текста из текстового файла
     * @param textFilePath Полный путь к текстовому файлу
     * @return текстовую строку, содержащую данные прочитанные из файла.
     * @throws Exception если файл не найден или произошла ошибка при чтении
     */
    public static String readTextFromFile(String textFilePath) throws Exception {
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

}
