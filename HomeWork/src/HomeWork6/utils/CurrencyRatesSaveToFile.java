package HomeWork6.utils;

import HomeWork6.dto.CurrencyRates;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Vitali Tsvirko
 */
public class CurrencyRatesSaveToFile {
    public void writeToFile (CurrencyRates dataContainer, DateFormat dateFormat, File filePath) throws IOException {
        String fileName = dataContainer.getCurrencyName()
                            + "_"
                            + dateFormat.format(dataContainer.getStartDate())
                            + "-"
                            + dateFormat.format(dataContainer.getEndDate())
                            + ".txt";

        File file = (filePath.exists())
                    ? new File(Path.of(filePath.toString(),fileName).toString())
                    : new File(fileName);

        try(FileWriter writer = new FileWriter(file)){
            for (Date date : dataContainer.getData().keySet()) {
                writer.write(dateFormat.format(date) + " : " + dataContainer.getData().get(date) + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Ошибка записи в файл.");
        }
    }

}
