package HomeWork6;

import HomeWork6.dto.CurrencyRates;
import HomeWork6.dto.enums.Banks;
import HomeWork6.dto.enums.Currency;
import HomeWork6.saver.CurrencyRatesFileSaver;
import HomeWork6.saver.exceptions.IllegalBankInFileException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class TestAdd {
    public static void main(String[] args) {
        CurrencyRates data = new CurrencyRates(Banks.BELAGROPROMBANK);
        CurrencyRates data2 = new CurrencyRates(Banks.BELAGROPROMBANK);

        data.addCurrencyData(Currency.USD, new Date(), 11.23);
        data.addCurrencyData(Currency.USD, new Date(2021,8,1), 12.23);
        data.addCurrencyData(Currency.USD, new Date(2020,9,1), 13.23);
        data.addCurrencyData(Currency.USD, new Date(2019,1,1), 14.23);

        data.printCurrencyRates(new SimpleDateFormat("dd.MM.yyyy"));

        data2.addCurrencyData(Currency.USD, new Date(2018,2,1), 11.23);
        data2.addCurrencyData(Currency.USD, new Date(2022,3,1), 12.23);
        data2.addCurrencyData(Currency.USD, new Date(2024,5,1), 13.23);
        data2.addCurrencyData(Currency.USD, new Date(2019,1,1), 14.23);


        System.out.println("\nmerge data and data2 \n");

        Map<Currency, Map<Date, Double>> allCurrencyRate = data2.getAllCurrencyRate();

        for (Currency currency : allCurrencyRate.keySet()) {
            Map<Date, Double> dateDoubleMap = allCurrencyRate.get(currency);
            data.addCurrencyData(currency, dateDoubleMap);
        }


        data.printCurrencyRates(new SimpleDateFormat("dd.MM.yyyy"));

        CurrencyRatesFileSaver saver = new CurrencyRatesFileSaver(2);

        try {
            saver.saveDataToFile(data2);
        } catch (IllegalBankInFileException e){
            System.out.println(e.getMessage());
            System.exit(-666);
        } catch (ParseException | IOException e){
            System.out.println(e.getMessage());
            System.exit(-667);
        }

        System.out.println("Данные успешно сохранены");



/*




        try {
            saver.readFromFile(new File("d:\\01. Vitali\\03. Inf\\01. Java\\Lessons\\JD1\\Белагропромбанк"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
*/





    }
}
