package HomeWork6.saver;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.enums.Banks;
import HomeWork6.dto.enums.Currency;
import HomeWork6.saver.exceptions.IllegalBankInFileException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Vitali Tsvirko
 */
public class CurrencyRatesFileSaver {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final Map<String, Banks> banks;
    private final Map<String, Currency> currency;
    private final int retryCount;

    /**
     * Конструктор
     * @param retryCount количество попыток для ввода имени файла
     */
    public CurrencyRatesFileSaver(int retryCount) {
        this.retryCount = retryCount;

        this.banks = Arrays.stream(Banks.values())
                .collect(Collectors.toMap(Banks::getName, bank -> bank));

        this.currency = Arrays.stream(Currency.values())
                .collect(Collectors.toMap(value -> value.name(), value -> value));
    }


    /**
     * Данный метод выполняет сохранения курсов валют в файл
     * @param dataContainer контейнер с данными
     * @return true если сохранено без ошибок, false если данные не были сохранены
     * @throws IllegalBankInFileException если указан файл с данными курсов валют другого банка
     * @throws IOException если произошли ошибки при работе с файлами
     * @throws ParseException если произошли ошибки при парсинге данных из существующего файла
     */
    public boolean saveDataToFile(CurrencyRates dataContainer) throws IllegalBankInFileException, IOException, ParseException {
        int retryCounter = 0;
        boolean error;
        boolean fileRewrite = false;
        Path path = null;

        do {
            try {

                error = false;
                path = userRequestTheFilePath(dataContainer.getBank());

            } catch (FileAlreadyExistsException e){
                //Файл не пуст и не является файлом хранения курсов
                System.out.println("Файл не пуст и не является файлом хранения курсов");
                path = Path.of(e.getMessage());
                fileRewrite = userRequestToRewriteExistFile();
                error = false;

            } catch (IllegalBankInFileException e){
                //В файле записаны данные другого банка
                System.out.println(e.getMessage());
                error = true;
                ++retryCounter;

            } catch (IOException e){
                //Ошибка чтения файла
                throw new IOException(e.getMessage());
            }
        } while ((fileRewrite) | (error && retryCounter < this.retryCount));



        if (error) {
            throw new IllegalBankInFileException ("Превышен лимит ввода данных файла.");
        }


        try {
            writeToFile(dataContainer, path, fileRewrite);

        } catch (ParseException e){
            throw new ParseException(e.getMessage(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Данный метод выводит в консоль запрос на перезапись существующего файла
     * @return true если файл необходимо перезаписать, false если нет
     */
    private boolean userRequestToRewriteExistFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Перезаписать существующий файл (Да/Нет)?");

        String answer = scanner.next();

        return (answer.equalsIgnoreCase("Да") || answer.equalsIgnoreCase("Д"));
    }


    /**
     * Данный метод выводит в консоль запрос на путь к фалй для сохранения результата
     * @param bank банк для которого выполняется сохранение результатов
     * @return {@code Path}
     * @throws IllegalBankInFileException если указан файл с данными курсов валют другого банка
     * @throws IOException если произошли ошибки при работе с файлами
     * @throws ParseException если произошли ошибки при парсинге данных из существующего файла
     */
    private Path userRequestTheFilePath (Banks bank) throws IllegalBankInFileException, FileAlreadyExistsException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя файла для сохранения курсов валют банка " + bank.getName() + ":");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        if (file.exists()){

            if (file.length() != 0) {
                Banks bankInFile = readBankNameFromFile(file);

                if (bankInFile == null){
                    throw new FileAlreadyExistsException(file.getPath());
                }

                if (bankInFile != bank) {
                    throw new IllegalBankInFileException("В указанном файле располагаются данные банка "
                                                        + bankInFile.getName()
                                                        + ". Запись в указанный файл не возможна.");
                }
            }

            return Path.of(file.getPath());

        } else {
            Path path;

            if (filePath.matches("^[\\w,\\s-]+\\.[A-Za-z]{3}$")){
                path = Path.of(System.getProperty("user.dir"), filePath);
            } else {
                path = Path.of(System.getProperty("user.dir"), bank.name() + ".txt");
            }

            System.out.println("Указан не верный путь к файлу. Результаты будут сохранены в: " + path);
            return path;
        }

    }


    /**
     * Данный метод выполняет запись в файл
     * @param dataContainer контейнер с данными
     * @param filePath пусть к фалй с указанием имени
     * @param fileRewrite флаг разрешения перезаписи файла
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws ParseException
     */
    private void writeToFile(CurrencyRates dataContainer, Path filePath, boolean fileRewrite) throws IOException, IllegalArgumentException, ParseException {
        File file = new File(filePath.toString());

        if (file.exists() && !fileRewrite){
            CurrencyRates dataFromFile = readFromFile(file);

            CurrencyRates dataToWrite = new CurrencyRates(dataContainer.getBank());

            dataToWrite.addCurrencyData(dataContainer);
            dataToWrite.addCurrencyData(dataFromFile);

            write(dataToWrite, file);
        } else {
            write(dataContainer, file);
        }

    }


    private void write(CurrencyRates dataContainer, File file) throws IOException {
        try(FileWriter writer = new FileWriter(file)){
            writer.write(dataContainer.getBank().getName() + "\n");

            Map<Currency, Map<Date, Double>> allCurrencyRate = dataContainer.getAllCurrencyRate();

            for (Currency currency : allCurrencyRate.keySet()) {
                Map<Date, Double> rates = allCurrencyRate.get(currency);
                for (Date date : rates.keySet()) {
                    writer.write(currency.name() + " : " + dateFormat.format(date) + " : " + rates.get(date) + "\n");
                }
            }

        } catch (IOException e) {
            throw new IOException("Ошибка записи в файл.");
        }
    }


    /**
     * Данный метод выполняет чтение банка {@code enum Banks} из файла с сохраненными курсами
     * @param file файл с сохраненными курсами
     * @return {@code enum Banks} из файла {@code file}
     * @throws IOException
     */
    private Banks readBankNameFromFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return getBankFromString(bufferedReader.readLine());
        } catch (IOException e) {
            throw new IOException("Ошибка чтения файла");
        }
    }


    /**
     * Данный методы выполняет чтение данный о курсах валют из файла
     * @param filePath путь к файлу с указанием имени
     * @return {@code CurrencyRates} с данными прочитанными из файла
     * @throws IOException
     * @throws ParseException
     */
    public CurrencyRates readFromFile (File filePath) throws IOException, ParseException {
        String textLine;
        CurrencyRates fileData;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            textLine = bufferedReader.readLine();

            Banks bank = getBankFromString(textLine);
            if (bank == null){
                throw new IllegalArgumentException("Неверный формат файла");
            }

            fileData = new CurrencyRates(bank);

            textLine = bufferedReader.readLine();
            while(textLine != null){
                addCurrencyRateFromString (fileData, textLine);
                textLine = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Файл не найден. Путь к файлу:" + filePath);
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), 0);
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла");
        }

        return fileData;
    }


    private Banks getBankFromString(String text){
        return this.banks.get(text);
    }


    private void addCurrencyRateFromString (CurrencyRates fileData, String text) throws ParseException{
        String[] data = text.split(":");

        if (data.length != 3){
            throw new ParseException("Ошибка при чтении информации из существующего файла с данными.", 0);
        }

        Currency currency = this.currency.get(data[0].trim());

        if ( currency != null ){
            try {
                fileData.addCurrencyData(currency,
                                                dateFormat.parse(data[1].trim()),
                                                Double.parseDouble(data[2].trim()));
            } catch (ParseException | NumberFormatException e) {
                throw new ParseException("Ошибка при чтении информации из существующего файла с данными.", 0);
            }
        }

    }

}
