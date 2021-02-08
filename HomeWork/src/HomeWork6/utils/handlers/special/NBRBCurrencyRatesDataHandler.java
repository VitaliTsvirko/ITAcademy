package HomeWork6.utils.handlers.special;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.DateRange;
import HomeWork6.dto.enums.Currency;
import HomeWork6.utils.loaders.SiteDataLoader;
import HomeWork6.utils.handlers.dto.ICurrencyRatesDataHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Данный класс предназначен для получения официального курса белорусского рубля НБРБ.
 * Класс выполняет построение запросов к сайту НБРБ и обработку полученных данных.
 *
 * <p>Класс работает с использованием  * <a href="https://www.nbrb.by/apihelp/exrates">API получения официального курса
 * белорусского рубля по отношению к иностранным валютам, устанавливаемого Национальным банком Республики Беларусь</a></p>
 *
 * <p>
 * API банка позволяет выполнять следующие запросы:
 *  <ul>
 *      <li>для конкретной валюты</li>
 *      <li>на конкретную дату</li>
 *      <li>на диапазон дат</li>
 *  </ul>
 * </p>
 *
 * <p>Класс позволяет получать курсы валют описанные в {@code enum Currency}</p>
 *
 * <p>Результат курсов валют записывается в контейнер {@code CurrencyRates}</p>
 *
 * <p><a href="https://www.nbrb.by/api/exrates/rates/145">Пример запроса для USD на текущую дату</a></p>
 * <p><a href="https://www.nbrb.by/API/ExRates/Rates/Dynamics/145?startDate=2021-1-1&endDate=2021-2-1">Пример запроса для USD на текущую даты 01.01.2021 - 01.02.2021</a></p>
 *
 * @author Vitali Tsvirko
 *
 * @see ICurrencyRatesDataHandler
 * @see CurrencyRates
 * @see DateRange
 * @see Currency
 * @see <a href="https://www.nbrb.by/apihelp/exrates">Описание API</a>
 */

public class NBRBCurrencyRatesDataHandler implements ICurrencyRatesDataHandler {
    private final DateFormat sourceDataDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Данный метод выполняет запрос курсов валют
     * @param dataLoader объект который выполняет запрос данных у сайта банка
     * @param dataContainer контейнер в который необходимо записать данные курсов валют
     * @param dateRange объект содержащий дату, или диапазон дат, за который необходимо получить курсы валют
     * @throws NumberFormatException если произошла ошибка парсинга полученных данных
     */
    @Override
    public void requestCurrencyRates(SiteDataLoader dataLoader, CurrencyRates dataContainer, DateRange dateRange) throws NumberFormatException {
        for (Currency currency : Currency.values()) {
            String url = urlBuilder(currency, dateRange.getStartDate(), dateRange.getEndDate());
            String data = dataLoader.load(url);
            dataContainer.addCurrencyData(currency, parseData(data));
        }
    }


    /**
     * Данный метод выполняет парсинг данных о курсах валют полученных от банка
     * @see <a href="https://www.nbrb.by/apihelp/exrates">Описание API</a>
     * @param data строка с курсами валют полученная от сайта банка
     * @return {@code Map} содержащую дату, и значение курса валюты
     * @throws NumberFormatException если произошла ошибка парсинга данных
     */
    private Map<Date, Double> parseData(String data) throws NumberFormatException{
        Map<Date, Double> result = new TreeMap<>();

        String[] lineArray = data.replaceAll("(\\[)|(\\])|(\")", "")
                .replaceAll("^(\\{)|(\\})$", "")
                .split("\\},\\{");

        if (lineArray.length == 0){
            throw new NumberFormatException("Получены пустые данные");
        }

        for (String line : lineArray) {
            //Получаем объект JSON
            String[] dataFields = line.split(",");

            try {
                String stringDateTime = dataFields[1].split(":")[1];

                Date date = this.sourceDataDateFormat.parse(stringDateTime.substring(0, stringDateTime.lastIndexOf("T")));
                Double rate = Double.parseDouble(dataFields[2].split(":")[1]);

                result.put(date, rate);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ParseException e) {
                throw new NumberFormatException("Ошибка в полученных данных");
            }
        }

        return result;
    }


    /**
     * Данный метод выполняет построение URL для запроса данных с сайта
     * @param currencyName валюта для которой выполняется запрос
     * @param startDate начальная дата для которой выполнятся запрос
     * @param endDate конечная дата (включительно) для которой выполнятся запрос
     * @return строка содержащая URL
     */
    private String urlBuilder(Currency currencyName, Date startDate, Date endDate) {
        return "https://www.nbrb.by/API/ExRates/Rates/Dynamics/"
                + getCurrencyId(currencyName)
                + "?"
                + "startDate="
                + this.sourceDataDateFormat.format(startDate)
                + "&"
                + "endDate="
                + this.sourceDataDateFormat.format(endDate);
    }


    /**
     * Данный метод возвращает ID валюты НБРБ
     * @param currency валюта для которой выполняется запрос
     * @return ID валюты НБРБ. Вернет пустую строку если запрашиваемая валюта не найдена
     */
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

}
