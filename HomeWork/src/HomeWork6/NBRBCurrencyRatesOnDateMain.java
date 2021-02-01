package HomeWork6;

import HomeWork6.utils.SiteDataLoader;
import HomeWork6.utils.nbrb.NBRBCurrencyRates;
import HomeWork6.utils.nbrb.test.SiteDataLoaderForTest;

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

        List<Date> dateRange = new ArrayList<>(2);

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




        currencyRates = getCurrencyRates(loader, NBRBCurrencyRates.Currency.USD, dateRange);
        printCurrencyRates(currencyRates, dateFormat, NBRBCurrencyRates.Currency.USD);
        currencyRates = getCurrencyRates(loader, NBRBCurrencyRates.Currency.EUR, dateRange);
        printCurrencyRates(currencyRates, dateFormat, NBRBCurrencyRates.Currency.EUR);
        currencyRates = getCurrencyRates(loader, NBRBCurrencyRates.Currency.RUB, dateRange);
        printCurrencyRates(currencyRates, dateFormat, NBRBCurrencyRates.Currency.RUB);

    }


    /**
     * Данный метод выводит на экран курсы валют за период
     * @param currencyRates {@code Map} с данными о курсах валюты
     * @param dateFormat формат даты для вывода
     */
    public static void printCurrencyRates(Map<Date, Double> currencyRates, DateFormat dateFormat, NBRBCurrencyRates.Currency currencyName){
        System.out.println("Курс " + currencyName.name() + ":");
        for (Date date : currencyRates.keySet()) {
            System.out.println(dateFormat.format(date) + " : " + currencyRates.get(date));
        }
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param loader объект который осуществляет загрузку данных с сайта
     * @param currencyName валюта для которой необходимо получить курс
     * @param dateRange {@code List<Date>} содержащий дату или диапазон дат (начальная, конечная).
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     */
    public static Map<Date, Double> getCurrencyRates (NBRBCurrencyRates loader, NBRBCurrencyRates.Currency currencyName, List<Date> dateRange){
        try {
            return (dateRange.size() == 1)
                    ? loader.getCurrencyRatesOnDate(currencyName, dateRange.get(0))
                    : loader.getCurrencyRatesOnDateRange(currencyName, dateRange.get(0), dateRange.get(1));

        } catch (ParseException |  RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(-200);
        }

        return null;
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
