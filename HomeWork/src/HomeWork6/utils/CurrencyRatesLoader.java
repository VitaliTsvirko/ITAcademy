package HomeWork6.utils;

import HomeWork6.task2.utils.SiteLoader;

/**
 * Created by Vitali Tsvirko
 */
public class CurrencyRatesLoader extends SiteLoader {
    public enum Banks{
        ALPHABANK("https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates"),
        BELARUSBANK("292"),
        BPSBANK("298");

        private String urlToSite;

        Banks(String id) {
            this.urlToSite = urlToSite;
        }

        public String getUrlToSite(){
            return this.urlToSite;
        }
    }

    @Override
    public double load(Currency currencyName) {
        return 0;
    }

    @Override
    protected double handle(String content, Currency currencyName) {
        return 0;
    }
}
