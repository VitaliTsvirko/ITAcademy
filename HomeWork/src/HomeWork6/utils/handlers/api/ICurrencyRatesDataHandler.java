package HomeWork6.utils.handlers.api;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.DateRange;
import HomeWork6.utils.loaders.SiteDataLoader;


public interface ICurrencyRatesDataHandler {
    /**
     * Данный метод выполняет запрос курсов валют
     * @param dataLoader объект который выполняет запрос данных у сайта банка
     * @param dataContainer контейнер в который необходимо записать данные курсов валют
     * @param dateRange объект содержащий дату, или диапазон дат, за который необходимо получить курсы валют
     * @throws NumberFormatException если произошла ошибка парсинга полученных данных
     */
    void requestCurrencyRates(SiteDataLoader dataLoader, CurrencyRates dataContainer, DateRange dateRange) throws NumberFormatException;
}
