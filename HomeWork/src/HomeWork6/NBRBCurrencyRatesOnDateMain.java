package HomeWork6;

import HomeWork6.dto.*;
import HomeWork6.dto.enums.Banks;
import HomeWork6.dto.enums.Currency;
import HomeWork6.utils.loaders.SiteDataLoader;
import HomeWork6.utils.handlers.HandlerSelector;
import HomeWork6.utils.handlers.dto.ICurrencyRatesDataHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vitali Tsvirko
 */
public class NBRBCurrencyRatesOnDateMain {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        DateRange dateRange = dateRangeBuild(args, dateFormat);

        SiteDataLoader loader = new SiteDataLoader();

        List<ICurrencyRatesDataHandler> dataHandlers = new ArrayList<>();
        List<CurrencyRates> currencyRatesContainers = new ArrayList<>();

        initMessage(dateRange);
        /*
            Main process
         */

        List<Banks> selectedBanks = userBanksRequester();

        /*
            Инициализация обработчиков и контейнеров
         */
        for (Banks bank : selectedBanks) {
            //Инициализация обработчиков для каждого банка
            dataHandlers.add(HandlerSelector.getHandler(bank));

            //Инициализация контейнеров для хранения курсов валют
            currencyRatesContainers.add(new CurrencyRates(bank));
        }


        /*
            Запрос курсов валют
         */
        for (int i = 0; i < dataHandlers.size(); i++) {
            try{
                dataHandlers.get(i).requestCurrencyRates(loader, currencyRatesContainers.get(i), dateRange);
            } catch (NumberFormatException | NullPointerException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }



        }

        /*
            Вывод результатов в консоль
         */
        for (CurrencyRates currencyRatesContainer : currencyRatesContainers) {
            System.out.println("Банк " + currencyRatesContainer.getBank().getName() + ":");
            currencyRatesContainer.printCurrencyRates(dateFormat);
        }

    }




    /**
     * Данный метод выполняет запрос у пользователя списка банков для которых необходимо вывести курсы
     * @return список банков для которых необходимо вывести курсы
     */
    public static List<Banks> userBanksRequester(){

        System.out.println("Выберите банк:");

        int number = 0;
        for (Banks bank : Banks.values()) {
            System.out.println(++number + ". "  + bank.getName());
        }
        System.out.println((Banks.values().length + 1) + ". " + "Все банки");


        Scanner scanner = new Scanner(System.in);
        int selectionResult = -1;

        do{
            System.out.print("Введите номер банка (0 - выход):");
            try {
                selectionResult = scanner.nextInt();
                if (selectionResult == 0){
                    System.exit(0);
                }
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        } while ((selectionResult > Banks.values().length + 1) || (selectionResult < 0));


        List<Banks> selectedBanks = new ArrayList<>();

        if (selectionResult == Banks.values().length + 1) {
            selectedBanks.addAll(Arrays.asList(Banks.values()));
        } else {
            selectedBanks.add(Banks.values()[selectionResult - 1]);
        }

        return selectedBanks;
    }


    /**
     * Данный метод выводит в консоль сообщения соедржащее:
     * <ul>
     *     <li>Перечь банков из которых можно запрашивать курсы валют</li>
     *     <li>Перечь валют для которых можно запрашивать курсы</li>
     *     <li>Дата (или диапазон) для которых будет выполнен запрос курсов валют</li>
     * </ul>
     *
     * @param dateRange объект содержащий даты для которых будет выполнен запрос
     */
    public static void initMessage(DateRange dateRange){
        String[] banksArray = Arrays.stream(Banks.values())
                                    .map(Banks::getName)
                                    .toArray(String[]::new);

        String[] currencyArray = Arrays.stream(Currency.values())
                                        .map(currency -> currency.name())
                                        .toArray(String[]::new);

        System.out.println("Данное приложение запрашивает курсы валют");
        System.out.println("Поддерживается работа со следующими банками: " + String.join(", ", banksArray));
        System.out.println("Поддерживается работа со следующими валютами: " + String.join(", ", currencyArray));

        System.out.println("");

        if (dateRange.isOneDay()){
            System.out.println("Курсы валют будут выведены на дату: " + dateRange.getStartDateString());
        } else {
            System.out.println("Курсы валют будут выведены на диапазон дат: " + dateRange.getStartDateString() + " - " + dateRange.getEndDateString());
        }

        System.out.println("");
    }


    /**
     * Данный метод выполняет парсинг дат
     * @param args строка содержащая дату или перечень дат
     * @param dateFormat формат даты
     * @return объект {@code DateRange} содержащий переданный в строке диапазон дат
     * @see DateRange
     */
    public static DateRange dateRangeBuild(String[] args, DateFormat dateFormat){
        DateRange dateRange = null;
        try {
            if (args.length == 0) {
                dateRange = new DateRange(dateFormat);
            } else {
                dateRange = new DateRange(args[0], dateFormat);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(-123);
        }

        return dateRange;
    }

}
