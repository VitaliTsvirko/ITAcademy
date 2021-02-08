package HomeWork6.dto;

import HomeWork6.dto.enums.Banks;
import HomeWork6.dto.enums.Currency;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Данный класс предназначен для хранения данных курсов валют
 *
 * <p> Данные хранятся в {@code Map Map<Currency, Map<Date, Double>>}</p>
 *
 * @author Vitali Tsvirko
 *
 * @see Currency
 * @see DateRange
 * @see Banks
 */
public class CurrencyRates {
    private Map<Currency, Map<Date, Double>> data = new TreeMap<>();
    private final Banks bank;

    /**
     * Конструктор
     * @param bank банк для которого создается объект для хранения курсов валют
     * @see Banks
     */
    public CurrencyRates(Banks bank) {
        this.bank = bank;
        for (Currency currency : Currency.values()) {
            this.data.put(currency, new TreeMap<>());
        }
    }

    /**
     * Данный метод добавляет данные о курсах валют
     * @param currencyName валюта {@code enum Currency}
     * @param date дата на которую задан курс
     * @param rate курс валюты по отношению к белорусскому рублю
     */
    public void addCurrencyData(Currency currencyName, Date date, Double rate) {
        Map<Date, Double> currencyRateMap = this.data.get(currencyName);
        currencyRateMap.put(date, rate);
   }

    /**
     * Данный метод добавляет данные о курсах валют
     * @param currencyName валюта {@code enum Currency}
     * @param currencyRateData {@code Map} содержащая данные о курсах валюты и датах
     */
    public void addCurrencyData(Currency currencyName, Map<Date, Double> currencyRateData) {
        Map<Date, Double> currencyRateMap = this.data.get(currencyName);
        currencyRateMap.putAll(currencyRateData);
    }


    /**
     * Данный метод возвращает данные о курсах валют для конкретной валюты
     * @param currencyName валюта {@code enum Currency}
     * @return {@code Map} содержащая данные о курсах валюты и датах
     */
    public Map<Date, Double> getCurrencyRate(Currency currencyName){
        return this.data.get(currencyName);
    }

    /**
     * Данный метод возвращает данные о курсах валют для всех валют
     * @return {@code Map<Currency, Map<Date, Double>>} содержащая данные о курсах валюты и датах
     */
    public Map<Currency, Map<Date, Double>> getAllCurrencyRate(){
        return this.data;
    }


    /**
     * Данный метод возвращает наименование банка
     * @return наименование банка {@code enum Banks}
     * @see Banks
     */
    public Banks getBank() {
        return bank;
    }


    /**
     * Данный метод выводит в консоль данные о курсах валют для всех валют
     * @param dateFormat формат даты
     */
    public void printCurrencyRates(DateFormat dateFormat){
        for (Currency currency : Currency.values()) {
            Map<Date, Double> currencyRateMap = this.data.get(currency);

            for (Date date : currencyRateMap.keySet()) {
                printToConsole(currency, date, currencyRateMap.get(date), dateFormat);
            }
        }
    }

    /**
     * Данный метод выводит в консоль данные о курсах валют для конкретной валюты
     * @param currencyName валюта для которой необходимо вывести данные
     * @param dateFormat формат даты
     */
    public void printCurrencyRates(Currency currencyName, DateFormat dateFormat){
        Map<Date, Double> currencyRateMap = this.data.get(currencyName);

        for (Date date : currencyRateMap.keySet()) {
            printToConsole(currencyName, date, currencyRateMap.get(date), dateFormat);
        }
    }


    /**
     * Данный метод выводит в консоль строку с данным о курсе валют
     * @param currency валюта
     * @param date дата
     * @param rate курс валюты
     * @param dateFormat формат даты
     */
    private void printToConsole(Currency currency, Date date, Double rate, DateFormat dateFormat){
        System.out.println(currency.name() + ": " + dateFormat.format(date)  + " = " + rate);
    }

}
