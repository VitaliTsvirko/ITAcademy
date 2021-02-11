package HomeWork6.utils.handlers.special;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.DateRange;
import HomeWork6.dto.enums.Currency;
import HomeWork6.utils.loaders.SiteDataLoader;
import HomeWork6.utils.handlers.api.ICurrencyRatesDataHandler;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Данный класс предназначен для получения официального курса белорусского рубля Альфа банка.
 * Класс выполняет построение запросов к сайту Альфа банка и обработку полученных данных.
 *
 * <p>Класс работает с использованием <a href="https://developerhub.alfabank.by/developerhub/site/pages/item-info.jag?name=partner.public&version=1.0.1&provider=admin&tab=doc#rate_model">Документация API</a></p>
 *
 * <p>API банка позволяет выполнять следующие запрос текущего курса валют (на дату запроса)</p>
 *
 * <p>Класс позволяет получать курсы валют описанные в {@code enum Currency}</p>
 *
 * <p>Результат курсов валют записывается в контейнер {@code CurrencyRates}</p>
 *
 * <p><a href="https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates">Пример запроса</a></p>
 *
 * @author Vitali Tsvirko
 *
 * @see ICurrencyRatesDataHandler
 * @see CurrencyRates
 * @see DateRange
 * @see Currency
 * @see <a href="https://developerhub.alfabank.by/developerhub/site/pages/item-info.jag?name=partner.public&version=1.0.1&provider=admin&tab=doc#rate_model">Документация API</a>
 */

public class AlphaBankCurrencyRatesDataHandler  implements ICurrencyRatesDataHandler{

    /**
     * Данный метод выполняет запрос курсов валют
     * @param dataLoader объект который выполняет запрос данных у сайта банка
     * @param dataContainer контейнер в который необходимо записать данные курсов валют
     * @param dateRange объект содержащий дату, или диапазон дат, за который необходимо получить курсы валют
     * @throws NumberFormatException если произошла ошибка парсинга полученных данных
     */
    @Override
    public void requestCurrencyRates(SiteDataLoader dataLoader, CurrencyRates dataContainer, DateRange dateRange) throws NumberFormatException {
        String url = urlBuilder();
        String data = dataLoader.load(url);
        Map<Currency, Double> currencyDoubleMap = parseData(data);
        Date date = new Date();

        for (Currency currency : Currency.values()) {
            dataContainer.addCurrencyData(currency, date, currencyDoubleMap.get(currency));
        }
    }


    /**
     * Данный метод выполняет парсинг данных о курсах валют полученных от банка
     * <p>Возвращает данные о всех курсах валют содержащихся в {@code enum Currency}</p>
     *
     * @param data строка с курсами валют, полученная от сайта банка
     * @return {@code Map} содержащую валюту {@code Currency}, и значение курса валюты
     * @throws NumberFormatException если произошла ошибка парсинга данных
     */
    private Map<Currency, Double> parseData(String data) throws NumberFormatException {
        Map<String, Double> allCurrencyRates = new TreeMap<>();
        Map<Currency, Double> result = new TreeMap<>();

        String[] lineArray = data.replaceAll("\"", "").split("},");
        String searchField = "{sellRate:";

        for (Currency currencyCode : Currency.values()) {
            for (String item : lineArray) {
                if (item.matches(".*(sellIso:"+ currencyCode + ").*(buyIso:BYN).*")){
                    try{
                        Double rate = Double.parseDouble(item.substring(item.indexOf(searchField) + searchField.length(), item.indexOf(",")));
                        result.put(currencyCode, rate);
                    } catch (NumberFormatException e){
                        throw new NumberFormatException("Ошибка в полученных данных");
                    }
                }
            }
        }

        return result;
    }


    /**
     * Данный метод возвращает URL для запроса данных с сайта
     * @return строку содержащую URL для запроса данных с сайта
     */
    private String urlBuilder() {
        return "https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates";
    }
}
