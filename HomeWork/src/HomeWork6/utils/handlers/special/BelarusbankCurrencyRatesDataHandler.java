package HomeWork6.utils.handlers.special;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.DateRange;
import HomeWork6.dto.enums.Currency;
import HomeWork6.utils.loaders.SiteDataLoader;
import HomeWork6.utils.handlers.dto.ICurrencyRatesDataHandler;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Данный класс предназначен для получения официального курса белорусского рубля Беларусбанка.
 * Класс выполняет построение запросов к сайту Беларусбанка и обработку полученных данных.
 *
 * <p>Класс работает с использованием <a href="https://belarusbank.by/ru/33139/forDevelopers/api/kursinfo">Документация API</a></p>
 *
 * <p>API банка позволяет выполнять следующие запрос текущего курса валют (на дату запроса) с указанием отделения банка</p>
 *
 * <p>Класс позволяет получать курсы валют описанные в {@code enum Currency}</p>
 * <p>Класс запрашивает данные о курсах валют для города Минска</p>
 *
 * <p>Результат курсов валют записывается в контейнер {@code CurrencyRates}</p>
 *
 * <p><a href="https://belarusbank.by/api/kursExchange?city=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA">Пример запроса</a></p>
 *
 * @author Vitali Tsvirko
 *
 * @see ICurrencyRatesDataHandler
 * @see CurrencyRates
 * @see DateRange
 * @see Currency
 * @see <a href="https://developerhub.alfabank.by/developerhub/site/pages/item-info.jag?name=partner.public&version=1.0.1&provider=admin&tab=doc#rate_model">Документация API</a>
 */

public class BelarusbankCurrencyRatesDataHandler  implements ICurrencyRatesDataHandler{

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
    public Map<Currency, Double> parseData(String data) throws NumberFormatException {
        Map<String, Double> allCurrencyRates = new TreeMap<>();
        Map<Currency, Double> result = new TreeMap<>();

        String[] lineArray = data.replaceAll("(\\[)|(\\])|(\")", "")
                .replaceAll("^(\\{)|(\\})$", "")
                .split("\\},\\{");

        if (lineArray.length == 0){
            throw new NumberFormatException("Получены пустые данные");
        }

        String[] dataObjectFields = lineArray[0].split(",");

        for (String dataObjectField : dataObjectFields) {
            String[] field = dataObjectField.split(":");
            if (field[0].contains("_out")){
                allCurrencyRates.put(field[0].replaceAll("_out", ""), Double.parseDouble(field[1]));
            }
        }

        for (Currency currency : Currency.values()) {
            result.put(currency, allCurrencyRates.get(currency.name()));
        }

        return result;
    }


    /**
     * Данный метод возвращает URL для запроса данных с сайта
     * @return строку содержащую URL для запроса данных с сайта
     */
    public String urlBuilder() {
        return "https://belarusbank.by/api/kursExchange?city=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA";
    }
}
