package HomeWork5.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Vitali Tsvirko
 */
public class PathToFileBuilder {

    /**
     * Данный метод возвращает полный пусть к файлу fileName расположенному в папке resources текущего пакета
     * @param fileName имя файла
     * @return строку содержащую полный пусть к файлу fileName
     * @throws FileNotFoundException если файл не найден
     */
    public static String buildPathToFileInCurrentProject(String fileName, String... additionalFolders) throws FileNotFoundException {
        Path currentRelativePath = Paths.get("", additionalFolders);
        String filePath = currentRelativePath.toAbsolutePath().toString() + File.separator + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            return filePath;
        } else {
            throw new FileNotFoundException("Файл не найден. Путь к файлу:" + filePath);
        }
    }

}
