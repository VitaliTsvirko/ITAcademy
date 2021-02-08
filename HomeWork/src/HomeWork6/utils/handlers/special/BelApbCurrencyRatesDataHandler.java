package HomeWork6.utils.handlers.special;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.DateRange;
import HomeWork6.dto.enums.Currency;
import HomeWork6.utils.loaders.SiteDataLoader;
import HomeWork6.utils.handlers.dto.ICurrencyRatesDataHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 * https://www.belapb.by/rus/faq_service/
 *
 */

/**
 * Данный класс предназначен для получения официального курса белорусского рубля .
 * Класс выполняет построение запросов к сайту Белагропромбанка и обработки полученных данных.
 *
 * <p>Класс работает с использованием <a href="https://www.belapb.by/rus/faq_service/">Документация API</a></p>
 *
 * <p>API банка позволяет выполнять запрос на получение курсов всех валют на указанную дату (параметр: ondate в GET запросе) </p>
 * <p>Для получения курсов валют за период происходит последовательный запрос данный курсов на каждый день из диапазона дат </p>
 *
 * <p>Класс позволяет получать курсы валют описанные в {@code enum Currency}</p>
 *
 * <p>Результат курсов валют записывается в контейнер {@code CurrencyRates}</p>
 *
 * <p><a href="https://belapb.by/CashExRatesDaily.php?ondate=02/07/2021">Пример запроса</a></p>
 *
 * @author Vitali Tsvirko
 *
 * @see ICurrencyRatesDataHandler
 * @see CurrencyRates
 * @see DateRange
 * @see Currency
 * @see <a href="https://www.belapb.by/rus/faq_service/">Документация API</a>
 */

public class BelApbCurrencyRatesDataHandler implements ICurrencyRatesDataHandler{
    private final DateFormat sourceDataDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Данный метод выполняет запрос курсов валют
     * @param dataLoader объект который выполняет запрос данных у сайта банка
     * @param dataContainer контейнер в который необходимо записать данные курсов валют
     * @param dateRange объект содержащий дату, или диапазон дат, за который необходимо получить курсы валют
     * @throws NumberFormatException если произошла ошибка парсинга полученных данных
     */
    @Override
    public void requestCurrencyRates(SiteDataLoader dataLoader, CurrencyRates dataContainer, DateRange dateRange) throws NumberFormatException {

        Map<Currency, Double> dayCurrencyRate;

        List<Date> dateRangeList = dateRange.getDateRangeList();

        for (Date date : dateRangeList) {
            String url = urlBuilder(date);

            String data = dataLoader.load(url);
            dayCurrencyRate = parseData(data);

            for (Currency currency : Currency.values()) {
                dataContainer.addCurrencyData(currency, date, dayCurrencyRate.get(currency));
            }
        }

    }

    /**
     * Данный метод выполняет парсинг данных о курсах валют полученных от банка
     * <p>Возвращает данные о всех курсах валют содержащихся в {@code enum Currency}</p>
     * <p>Данные о курсах валют возвращаются для города Минск {@code <CityId>24811</CityId>},
     * для первого встретившегося в результате {@code data} отделения {@code <BankId>} </p>
     *
     * @param data строка с курсами валют, полученная от сайта банка
     * @return {@code Map} содержащую валюту {@code Currency}, и значение курса валюты
     * @throws NumberFormatException если произошла ошибка парсинга данных
     */
    public Map<Currency, Double> parseData(String data) throws NumberFormatException {
        Map<Currency, Double> result = new TreeMap<>();

        List<XmlElement> xmlElements = new ArrayList<>();

        String normalizeData = data.replaceAll("(\r)|(\n)", "");

        Pattern pattern = Pattern.compile("<Currency Id=\"\\d+\">(.*?)</Currency>");
        Matcher matcher = pattern.matcher(normalizeData);

        Integer searchBankId = null;
        int searchCityId = 24811; // г. Минск

        while (matcher.find()) {
            String xmlElementSting = normalizeData.substring(matcher.start(), matcher.end());

            int cityId = Integer.parseInt(xmlElementSting.substring(xmlElementSting.indexOf("<CityId>") + "<CityId>".length(), xmlElementSting.indexOf("</CityId>")));
            int bankId = Integer.parseInt(xmlElementSting.substring(xmlElementSting.indexOf("<BankId>") + "<BankId>".length(), xmlElementSting.indexOf("</BankId>")));

            if (cityId == searchCityId && searchBankId == null){
                searchBankId = bankId;
            }

            if (cityId == searchCityId && bankId == searchBankId){
                XmlElement xmlElement = new XmlElement();

                xmlElement.charCode = xmlElementSting.substring(xmlElementSting.indexOf("<CharCode>") + "<CharCode>".length(), xmlElementSting.indexOf("</CharCode>"));
                xmlElement.rateSell = Double.parseDouble(xmlElementSting.substring(xmlElementSting.indexOf("<RateSell>") + "<RateSell>".length(), xmlElementSting.indexOf("</RateSell>")));
                xmlElement.cityId = cityId;
                xmlElement.bankId = bankId;

                xmlElements.add(xmlElement);
            }
        }

        for (Currency currency : Currency.values()) {
            List<XmlElement> collect = xmlElements.stream()
                    .filter(value -> value.charCode.equals(currency.name()))
                    .collect(Collectors.toList());

            if (collect.size() != 0){
                result.put(currency, collect.get(0).rateSell);
            }
        }

        return result;
    }


    /**
     * Данный метод возвращает URL для запроса данных с сайта
     * @param date дата для которой запрашиваются курсы валют
     * @return строку содержащую URL для запроса данных с сайта
     */
    public String urlBuilder(Date date) {
        return "https://belapb.by/CashExRatesDaily.php?ondate="
                + sourceDataDateFormat.format(date);
    }


    /**
     * Данный класс предназначен для хранения части данных полученных от сайта (XML элемент)
     */
    private class XmlElement {
        public String charCode;
        public double rateSell;
        public int cityId;
        public int bankId;
    }
}