package HomeWork6.task2;

import HomeWork6.task2.utils.AlphaBankLoader;
import HomeWork6.task2.utils.NBRBLoader;
import HomeWork6.task2.utils.SiteLoader;

/**
 * Created by Vitali Tsvirko
 */
public class CurrencyRatesMain {
    public static void main(String[] args) {
        NBRBLoader loader = new NBRBLoader();

        try {
            System.out.println("Курс 1 ЕВРО " + loader.load(SiteLoader.Currency.EUR));
            System.out.println("Курс 100 Российских рублей " + loader.load(SiteLoader.Currency.RUB));
            System.out.println("Курс 1 Доллара США " + loader.load(SiteLoader.Currency.USD));
        } catch (RuntimeException e){
            e.getMessage();
        }


        System.out.println("\nАльфа банк\n");

        AlphaBankLoader alphaBankLoader = new AlphaBankLoader();

        try {
            System.out.println("Курс 1 ЕВРО " + alphaBankLoader.load(SiteLoader.Currency.EUR));
            System.out.println("Курс 100 Российских рублей " + alphaBankLoader.load(SiteLoader.Currency.RUB));
            System.out.println("Курс 1 Доллара США " + alphaBankLoader.load(SiteLoader.Currency.USD));
        } catch (RuntimeException e){
            e.getMessage();
        }

    }
}
