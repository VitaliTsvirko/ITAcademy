package HomeWork6.utils.handlers;

import HomeWork6.dto.enums.Banks;
import HomeWork6.utils.handlers.api.ICurrencyRatesDataHandler;
import HomeWork6.utils.handlers.special.AlphaBankCurrencyRatesDataHandler;
import HomeWork6.utils.handlers.special.BelApbCurrencyRatesDataHandler;
import HomeWork6.utils.handlers.special.BelarusbankCurrencyRatesDataHandler;
import HomeWork6.utils.handlers.special.NBRBCurrencyRatesDataHandler;

/**
 * Данный класс возвращает объект {@code ICurrencyRatesDataHandler} в зависимости от переданного банка
 *
 * @author Vitali Tsvirko
 *
 * @see ICurrencyRatesDataHandler
 * @see Banks
 */
public class HandlerSelector {
    /**
     * Данный метод возвращает объект {@code ICurrencyRatesDataHandler} в зависимости от переданного банка
     * @param bank банк для которого необходимо вернуть обработчик
     * @return объект {@code ICurrencyRatesDataHandler} в зависимости от переданного банка
     * @see ICurrencyRatesDataHandler
     * @see Banks
     */
    public static ICurrencyRatesDataHandler getHandler (Banks bank){
        switch (bank){
            case NBRB:
                return new NBRBCurrencyRatesDataHandler();
            case ALPHABANK:
                return new AlphaBankCurrencyRatesDataHandler();
            case BELARUSBANK:
                return new BelarusbankCurrencyRatesDataHandler();
            case BELAGROPROMBANK:
                return new BelApbCurrencyRatesDataHandler();
        }

        return null;
    }
}
