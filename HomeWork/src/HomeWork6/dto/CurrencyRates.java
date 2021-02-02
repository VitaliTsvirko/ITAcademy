package HomeWork6.dto;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vitali Tsvirko
 */
public class CurrencyRates {
    private Map<Date, Double> data = new TreeMap();
    private final Currency currencyName;
    private Date startDate;
    private Date endDate;

    public CurrencyRates(Currency currencyName) {
        this.currencyName = currencyName;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Currency getCurrencyName() {
        return currencyName;
    }

    public void addCurrencyRate (Date date, Double value){
        this.data.put(date, value);
    }

    public void clearCurrencyData(){
        this.data.clear();
        this.startDate = null;
        this.endDate = null;
    }

    public Map<Date, Double> getData() {
        return data;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
