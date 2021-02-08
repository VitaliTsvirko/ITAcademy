package HomeWork6.task2.utils;

import HomeWork6.task2.utils.SiteLoader;

/**
 * Created by Vitali Tsvirko
 */
public class AlphaBankLoader extends SiteLoader {

    @Override
    public double load(Currency currencyName) {
        return load("https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates", currencyName);
    }

    @Override
    protected double handle(String content, Currency currencyName) {
        String[] data = content.replaceAll("\"", "").split("},");
        String id = "{sellRate:";
        String currencyCode = currencyName.toString();

        for (String item : data) {
            if (item.matches(".*(sellIso:"+ currencyCode + ").*(buyIso:BYN).*")){
                return Double.parseDouble(item.substring(item.indexOf(id) + id.length(), item.indexOf(",")));
            }
        }

        return 0;
    }

}
