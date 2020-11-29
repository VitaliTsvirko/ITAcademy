/*
 * Author: Vitali Tsvirko
 * Date: 2020.11.29
 * Description:
 * 3. Изучить библиотеку (класс) Math из jdk. (https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html)
 */

package HomeWork1;

public class MathLibraryStudy {
    public static void main(String[] args) {
        double x;

        System.out.println(Math.max(100, 120));

        System.out.println("rounding");
        System.out.println("Наибольшее целое число, которое не больше value" + Math.floor(1075.673));
        System.out.println(Math.floor(1075.173));
        System.out.println(Math.round(145.66));
        System.out.println(Math.nextUp(123.77));
        System.out.println(Math.nextDown(123.0));
        System.out.println(" ");

        System.out.println(Math.IEEEremainder(134.67, 5));

        System.out.println("random");
        System.out.println(Math.random());
        System.out.println(Math.random() * 100);
        System.out.println("Случайное вещественное число от 11.5 до 90.6: " + (Math.random() * (90.6 - 11.5) + 11.5));
        System.out.println("Случайное целое число от 10 до 60: " + (int) (Math.random() * (90 - 10) + 10));

        System.out.println("Наименьшее целое число с плавающей точкой, которое не меньше value " + Math.ceil(145.66));
        System.out.println("Возвращает число double, которое представляет ближайшее к числу value целое число " + Math.rint(3.3));
        System.out.println("Возвращает число double, которое представляет ближайшее к числу value целое число " + Math.rint(3.503));

        System.out.println("Возвращает число a, возведенное в степень b " + Math.pow(2,2));
    }
}
