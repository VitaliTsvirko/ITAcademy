/*
 * Author: Vitali Tsvirko
 * Date: 2020.11.29
 * Description:
 * 4.* Создать СТАТИЧЕСКИЙ метод sleepIn рядом с методом main. (Взято с https://codingbat.com/prob/p187868).
	4.1 Данный метод будет принимает два параметра
	4.2 Будет отвечать на вопрос спать ли дальше (возвращать true либо false).
	4.3 Первый параметр boolean weekday обозначает рабочий день
	4.4 Второй параметр boolean vacation обозначает отпуск.
	4.5 Если у нас отпуск или не рабочий день то мы можем спать дальше
	4.6 На основании ответа от метода sleepIn вывести сообщение можем спать дальше или пора идти на работу
 *
 */

package HomeWork1;

public class SleepOrWork {
    public static void main(String[] args) {
        boolean weekday = true;
        boolean vacation = true;

        if (sleepIn(weekday, vacation)) {
            System.out.println("Можно спать дальше");
        }
        else {
            System.out.println("Пора идти на работу");
        }
    }

    private static Boolean sleepIn (boolean weekday, boolean vacation){
        return !weekday || vacation;
    }
}
