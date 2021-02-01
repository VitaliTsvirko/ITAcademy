package HomeWork6;

import HomeWork6.utils.AlphaBankLoader;
import HomeWork6.task2.utils.SiteLoader;

/**
 * Created by Vitali Tsvirko
 */
public class AlphaBankCurrencyRatesMain {
    public static void main(String[] args) {
        AlphaBankLoader loader = new AlphaBankLoader();

        String data = "{\"rates\":[{\"sellRate\":90.000000,\"sellIso\":\"EUR\",\"sellCode\":978,\"buyRate\":95.000000,\"buyIso\":\"RUB\",\"buyCode\":643,\"quantity\":1,\"name\":null,\"date\":\"2021-01-29T08:41:10.000Z\"},{\"sellRate\":74.000000,\"sellIso\":\"USD\",\"sellCode\":840,\"buyRate\":79.000000,\"buyIso\":\"RUB\",\"buyCode\":643,\"quantity\":1,\"name\":null,\"date\":\"2021-01-29T08:41:10.000Z\"},{\"sellRate\":1.190000,\"sellIso\":\"EUR\",\"sellCode\":978,\"buyRate\":1.240000,\"buyIso\":\"USD\",\"buyCode\":840,\"quantity\":1,\"name\":null,\"date\":\"2021-01-29T08:41:10.000Z\"},{\"sellRate\":3.350000,\"sellIso\":\"RUB\",\"sellCode\":643,\"buyRate\":3.550000,\"buyIso\":\"BYN\",\"buyCode\":933,\"quantity\":100,\"name\":\"российских рублей\",\"date\":\"2021-01-29T08:41:10.000Z\"},{\"sellRate\":3.100000,\"sellIso\":\"EUR\",\"sellCode\":978,\"buyRate\":3.230000,\"buyIso\":\"BYN\",\"buyCode\":933,\"quantity\":1,\"name\":\"евро\",\"date\":\"2021-01-29T08:41:10.000Z\"},{\"sellRate\":2.580000,\"sellIso\":\"USD\",\"sellCode\":840,\"buyRate\":2.650000,\"buyIso\":\"BYN\",\"buyCode\":933,\"quantity\":1,\"name\":\"доллар США\",\"date\":\"2021-01-29T08:41:10.000Z\"}]}";

        System.out.println(loader.handle2(data, SiteLoader.Currency.USD));
        System.out.println(loader.handle2(data, SiteLoader.Currency.EUR));
        System.out.println(loader.handle2(data, SiteLoader.Currency.RUB));


        try {
            //System.out.println("Курс 1 ЕВРО " + loader.load(SiteLoader.Currency.EUR));
            //System.out.println("Курс 100 Российских рублей " + loader.load(SiteLoader.Currency.RUB));
            System.out.println("Курс 1 Доллара США " + loader.load(SiteLoader.Currency.USD));
        } catch (RuntimeException e){
            e.getMessage();
        }
    }
}
