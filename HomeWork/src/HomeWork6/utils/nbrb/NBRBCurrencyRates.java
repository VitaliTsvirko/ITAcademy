package HomeWork6.utils.nbrb;

import HomeWork6.dto.Currency;
import HomeWork6.dto.CurrencyRates;
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
    public void getCurrencyRate (CurrencyRates dataContainer) throws ParseException{
        Date toDayDate = new Date();
        dataContainer.setStartDate(toDayDate);
        dataContainer.setEndDate(toDayDate);

        loadCurrencyRate(dataContainer);
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @param onDate дата на которую необходимо получить курс
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    public void getCurrencyRatesOnDate (CurrencyRates dataContainer, Date onDate) throws ParseException{
        dataContainer.setStartDate(onDate);
        dataContainer.setEndDate(onDate);
        loadCurrencyRate(dataContainer);
    }

    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @param currencyName валюта для которой необходимо получить курс
     * @param startData начальная дата
     * @param endData конечная дата (включительно)
     * @return {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты
     * @throws ParseException если произошла ошибка парсинга данных
     */
    public void getCurrencyRatesOnDateRange (CurrencyRates dataContainer, Date startData, Date endData) throws ParseException{
        dataContainer.setStartDate(startData);
        dataContainer.setEndDate(endData);
        loadCurrencyRate(dataContainer);
    }



    private String getCurrencyId (Currency currency){
        switch (currency){
            case USD:
                return "145";
            case EUR:
                return "292";
            case RUB:
                return "298";
            default:
                return "";
        }
    }


    /**
     * Данный метод возвращает {@code Map} содержащую в качестве ключа дату, в качестве значения курс валюты по отношению к белорусскому рублю
     * @throws ParseException если произошла ошибка парсинга данных
     */
    private void loadCurrencyRate (CurrencyRates dataContainer) throws ParseException {
        String data = this.dataLoader.load("https://www.nbrb.by/API/ExRates/Rates/Dynamics/"
                + getCurrencyId(dataContainer.getCurrencyName())
                + "?"
                + "startDate="
                + this.sourceDataDateFormat.format(dataContainer.getStartDate())
                + "&"
                + "endDate="
                + this.sourceDataDateFormat.format(dataContainer.getEndDate()));

        parseData(data, dataContainer);
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
    private void parseData (String data, CurrencyRates dataContainer) throws ParseException{
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

                dataContainer.addCurrencyRate(date, Double.parseDouble(value));
            } catch (ParseException e) {
                throw new ParseException("Ошибка в полученных данных", 0);
            }
        }
    }

}
