package HomeWork6;

import HomeWork6.dto.Currency;
import HomeWork6.dto.CurrencyRates;
import HomeWork6.utils.CurrencyRatesSaveToFile;
import HomeWork6.utils.nbrb.NBRBCurrencyRates;
import HomeWork6.utils.nbrb.test.SiteDataLoaderForTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class NBRBCurrencyRatesOnDateMain {
    public static void main(String[] args) {
        //NBRBCurrencyRates loader = new NBRBCurrencyRates(new SiteDataLoader());
        NBRBCurrencyRates loader = new NBRBCurrencyRates(new SiteDataLoaderForTest());

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Map<Date, Double> currencyRates = new TreeMap<>();

        List<Date> dateRange = new ArrayList<>(1);

        if (args.length == 0){
            System.err.println("Не задана дата.\nКурсы валют будут выведены на текущую дату");
            dateRange.add(new Date());
        } else {
            try{
                dateRange = getDateFromString(args[0]);
            } catch (IllegalArgumentException e){
                System.err.println(e.getMessage());
                System.exit(-100);
            }
        }


        System.out.print("Введите пусть для сохранения данных курсов валют:");
        Scanner scanner = new Scanner(System.in);

        String pathFromUser = scanner.next();
        File file = new File(pathFromUser);
        if (!file.exists()){
            System.err.println("Указан не верный путь. Файл будет создан: " + System.getProperty("user.dir"));
            file = new File(System.getProperty("user.dir"));
        }

        CurrencyRates usdCurrencyRates = new CurrencyRates(Currency.USD);
        CurrencyRates eurCurrencyRates = new CurrencyRates(Currency.EUR);
        CurrencyRates rubCurrencyRates = new CurrencyRates(Currency.RUB);


        getCurrencyRates(loader, usdCurrencyRates, dateRange);
        getCurrencyRates(loader, eurCurrencyRates, dateRange);
        getCurrencyRates(loader, rubCurrencyRates, dateRange);

/*        printCurrencyRates(usdCurrencyRates, dateFormat);
        printCurrencyRates(eurCurrencyRates, dateFormat);
        printCurrencyRates(rubCurrencyRates, dateFormat);*/



       CurrencyRatesSaveToFile fileSaver =  new CurrencyRatesSaveToFile();
       try {
           fileSaver.writeToFile(usdCurrencyRates, dateFormat, file);
           fileSaver.writeToFile(eurCurrencyRates, dateFormat, file);
           fileSaver.writeToFile(rubCurrencyRates, dateFormat, file);
       } catch (IOException e){
           e.getMessage();
       }





    }


    /**
     * Данный метод выводит на экран курсы валют за период
     * @param dateFormat формат даты для вывода
     */
    public static void printCurrencyRates(CurrencyRates dataContainer, DateFormat dateFormat){
        System.out.println("Курс " + dataContainer.getCurrencyName() + ":");
        for (Date date : dataContainer.getData().keySet()) {
            System.out.println(dateFormat.format(date) + " : " + dataContainer.getData().get(date));
        }
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param loader объект который осуществляет загрузку данных с сайта
     * @param dateRange {@code List<Date>} содержащий дату или диапазон дат (начальная, конечная).
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     */
    public static void getCurrencyRates (NBRBCurrencyRates loader, CurrencyRates dataContainer, List<Date> dateRange){
        try {
            if ((dateRange.size() == 1)) {
                loader.getCurrencyRatesOnDate(dataContainer, dateRange.get(0));
            } else {
                loader.getCurrencyRatesOnDateRange(dataContainer, dateRange.get(0), dateRange.get(1));
            }

        } catch (ParseException |  RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(-200);
        }
    }


    /**
     * Данный метод осуществляет преобразование строки в даты
     * Поддерживаются следующие форматы дат:
     * 01.02.2021
     * 01.01.2021-01.02.2021
     * 01.01.2021,01.02.2021
     * @param stringDates текстовая строка с датами
     * @return {@code List<Date>} содержащий дату или диапазон дат (начальная, конечная).
     * @throws IllegalArgumentException если был передана неверная дата
     */
    public static List<Date> getDateFromString (String stringDates) throws IllegalArgumentException{
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (stringDates.matches("\\d{1,2}.\\d{1,2}.\\d{4}")){
            try {
                List<Date> dateRange = new ArrayList<>(1);
                dateRange.add(dateFormat.parse(stringDates));
                return dateRange;
            } catch (ParseException e){
                throw new IllegalArgumentException("Передана неверная дата");
            }
        }

        if (stringDates.matches("(\\d{1,2}.\\d{1,2}.\\d{4})(,|-)(\\d{1,2}.\\d{1,2}.\\d{4})")) {
            try {
                List<Date> dateRange = new ArrayList<>(2);
                dateRange.add(dateFormat.parse(stringDates.split("(,|-)")[0]));
                dateRange.add(dateFormat.parse(stringDates.split("(,|-)")[1]));
                return dateRange;
            } catch (ParseException e){
                throw new IllegalArgumentException("Передана неверная дата");
            }
        }

        throw new IllegalArgumentException("Передана неверная дата");
    }


}
