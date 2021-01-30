package HomeWork6;

import HomeWork6.utils.NBRBLoader;
import HomeWork6.utils.SiteLoader;

/**
 * Created by Vitali Tsvirko
 */
public class NBRBCurrencyRatesMain {
    public static void main(String[] args) {
        NBRBLoader loader = new NBRBLoader();

        try {
            System.out.println("Курс 1 ЕВРО " + loader.load(SiteLoader.Currency.EUR));
            System.out.println("Курс 100 Российских рублей " + loader.load(SiteLoader.Currency.RUB));
            System.out.println("Курс 1 Доллара США " + loader.load(SiteLoader.Currency.USD));
        } catch (RuntimeException e){
            e.getMessage();
        }
    }
}
