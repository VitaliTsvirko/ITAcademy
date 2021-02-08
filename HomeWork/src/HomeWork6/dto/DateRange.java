package HomeWork6.dto;

import test.A;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Данный класс предназначен для хранения и обработки диапазона даты для запроса курсов валют
 *
 * <p>Формат даты с которой работает класс передаться в конструкторе {@code DateFormat dateFormat}</p>
 *
 * <p>Если задана только дата (один день), то {@code getStartDate} и {@code getEndDate} вернут одинаковые значения.
 * Проверить задан диапазон дан или дата можно с использованием метода {@code isOneDay()} </p>
 *
 * @author Vitali Tsvirko
 *
 * @see DateFormat
 */

public class DateRange {
    private final DateFormat dateFormat;
    private Date startDate;
    private Date endDate;
    private boolean isOneDay;

    /**
     * Конструктор
     * <p>Одиночная дата на момент создания</p>
     * @param dateFormat формат даты
     */
    public DateRange(DateFormat dateFormat){
        this.dateFormat = dateFormat;
        Date currentDate = new Date();
        this.startDate = currentDate;
        this.endDate = currentDate;
        this.isOneDay = true;
    }

    /**
     * Конструктор
     * <p>Дата или диапазон дат переданная в строке {@code String stringDates}</p>
     * <p>
     * Поддерживаются следующие форматы дат переданные в строке {@code stringDates}:
     * <ul>
     *     <li>01.02.2021</li>
     *     <li>01.01.2021-01.02.2021</li>
     *     <li>01.01.2021,01.02.2021</li>
     * </ul>
     * </p>
     *
     * @param dateFormat формат даты
     * @param stringDates текстовая строка с датами
     * @throws IllegalArgumentException если в строке передана неверная дата
     */
    public DateRange(String stringDates, DateFormat dateFormat) {
        this.dateFormat = dateFormat;

        if (stringDates.matches("\\d{1,2}.\\d{1,2}.\\d{4}")){
            try {
                Date date = dateFormat.parse(stringDates);
                this.startDate = date;
                this.endDate = date;
                this.isOneDay = true;
            } catch (ParseException e){
                throw new IllegalArgumentException("Передана неверная дата");
            }
        }

        if (stringDates.matches("(\\d{1,2}.\\d{1,2}.\\d{4})(,|-)(\\d{1,2}.\\d{1,2}.\\d{4})")) {
            try {
                this.startDate  = dateFormat.parse(stringDates.split("(,|-)")[0]);
                this.endDate = dateFormat.parse(stringDates.split("(,|-)")[1]);
                this.isOneDay = false;
            } catch (ParseException e){
                throw new IllegalArgumentException("Передана неверная дата");
            }
        }
    }

    /**
     * Данный метод возвращает начальную дату диапазона
     * @return начальную дату диапазона
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Данный метод возвращает конечную дату диапазона
     * @return начальную дату диапазона
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * Данный метод возвращает строку с начальной датой диапазона
     * Формат даты {@code DateFormat dateFormat} передается в конструкторе
     * @return строка с начальной дату диапазона
     */
    public String getStartDateString() {
        return dateFormat.format(startDate).toString();
    }

    /**
     * Данный метод возвращает строку с конечной датой диапазона
     * Формат даты {@code DateFormat dateFormat} передается в констуркторе
     * @return строка с конечной датой диапазона
     */
    public String getEndDateString() {
        return dateFormat.format(endDate).toString();
    }

    /**
     * Данный метод возвращает признак задан диапазон дат или дата
     * @return true если задана дата (один день)
     *         false если задан диапазон дат
     */
    public boolean isOneDay() {
        return isOneDay;
    }

    /**
     * Данный метод возвращает заданный формат даты
     * @return заданный формат даты {@code DateFormat}
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }


    /**
     * Данный метод возвращает список дат из диапазона с периодом 1 день
     * @return список дат из диапазона с периодом 1 день
     */
    public List<Date> getDateRangeList(){
        List<Date> result = new ArrayList<>();

        LocalDate start = LocalDate.of(this.startDate.getYear() + 1900, this.startDate.getMonth() + 1, this.startDate.getDate()) ;
        LocalDate end = LocalDate.of(this.endDate.getYear() + 1900, this.endDate.getMonth() + 1, this.endDate.getDate());

        Stream<LocalDate> dates = start.datesUntil(end.plusDays(1));
        List<LocalDate> list = dates.collect(Collectors.toList());

        for (LocalDate localDate : list) {
            result.add(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        return result;
    }
}
