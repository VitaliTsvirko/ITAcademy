package HomeWork6.utils.nbrb;

import HomeWork6.utils.SiteDataLoader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vitali Tsvirko
 */
public class NBRBCurrencyRates {
    public enum Currency{
        USD("145"),
        EUR("292"),
        RUB("298");

        private String id;

        Currency(String id) {
            this.id = id;
        }

        public String getId(){
            return this.id;
        }
    }

    private SiteDataLoader dataLoader;
    private final DateFormat sourceDataDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Конструктор
     * @param dataLoader объект который осуществляет загрузку данных с сайта
     */
    public NBRBCurrencyRates(SiteDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    /**
     * Данный метод возвращает курс валюты на текущий день
     * {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    public Map<Date, Double> getCurrencyRate (NBRBCurrencyRates.Currency currencyName) throws ParseException{
        Date toDayDate = new Date();
        return loadCurrencyRate(currencyName, toDayDate, toDayDate);
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @param onDate дата на которую необходимо получить курс
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    public Map<Date, Double> getCurrencyRatesOnDate (NBRBCurrencyRates.Currency currencyName, Date onDate) throws ParseException{
        return loadCurrencyRate(currencyName, onDate, onDate);
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @param startData начальная дата
     * @param endData конечная дата (включительно)
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    public Map<Date, Double> getCurrencyRatesOnDateRange (NBRBCurrencyRates.Currency currencyName, Date startData, Date endData) throws ParseException{
        return loadCurrencyRate(currencyName, startData, endData);
    }


    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @param startData начальная дата
     * @param endData конечная дата (включительно)
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    private Map<Date, Double> loadCurrencyRate (NBRBCurrencyRates.Currency currencyName, Date startData, Date endData) throws ParseException {
        String data = dataLoader.load("https://www.nbrb.by/API/ExRates/Rates/Dynamics/"
                + currencyName.getId()
                + "?"
                + "startDate="
                + this.sourceDataDateFormat.format(startData)
                + "&"
                + "endDate="
                + this.sourceDataDateFormat.format(endData));

        return parseData(data);
    }


    /**
     * Данный метод выполняет парсинг полученных данных
     * Данные от сайта приходят в формате: {"Cur_ID":145,"Date":"2021-01-01T00:00:00","Cur_OfficialRate":2.5789}
     * где  Cur_ID – внутренний код
     *      Date – дата, на которую запрашивается курс
     *      Cur_OfficialRate – курс*
     * @param data данные от сайта
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    private Map<Date, Double> parseData (String data) throws ParseException{
        Map<Date, Double> map = new TreeMap<>();

        String[] lineArray = data.replaceAll("(\\[)|(\\])|(\")", "")
                                 .replaceAll("^(\\{)|(\\})$", "")
                                 .split("\\},\\{");

        if (lineArray.length == 0){
            throw new ParseException("Получены пустые данные", 0);
        }

        for (String line : lineArray) {
            String[] dataFields = line.split(",");

            try {
                String stringDateTime = dataFields[1].split(":")[1];

                Date date = this.sourceDataDateFormat.parse(stringDateTime.substring(0, stringDateTime.lastIndexOf("T")));
                String value = dataFields[2].split(":")[1];

                map.put(date, Double.parseDouble(value));
            } catch (ParseException e) {
                throw new ParseException("Ошибка в полученных данных", 0);
            }
        }

        return map;
    }

}
