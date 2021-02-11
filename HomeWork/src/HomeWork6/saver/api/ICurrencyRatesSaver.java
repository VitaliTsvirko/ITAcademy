package HomeWork6.saver.api;

import HomeWork6.dto.CurrencyRates;

/**
 * Created by Vitali Tsvirko
 */
public interface ICurrencyRatesSaver {
    void saveData (CurrencyRates container);
}
