/*
 * Author: Vitali Tsvirko
 * Date: 2020.12.2
 * Description:
 * 7**. Создать СТАТИЧЕСКИЙ метод String toBinaryString(byte number) и возвращает двоичное представление числа.
 *  Теория https://planetcalc.ru/747. Если число отрицательное то выдавать обратный код. Пример:
 *	7.1 Вводим: 42.  Возвращает: 00101010
 *	7.2 Вводим: 15.  Возвращает: 00001111
 *	7.3 Вводим: -42. Возвращает: 11010101
 *	7.4 Вводим: -15. Возвращает: 11110000
 *
 */

package HomeWork1;

public class ByteToBinaryString {
    public static void main(String[] args) {
        System.out.println(toBinaryString((byte) 42));
        System.out.println(toBinaryString((byte) 15));
        System.out.println(toBinaryString((byte) -42));
        System.out.println(toBinaryString((byte) -15));
    }

    /**
     *
     * @param number Число типа byte
     * @return Возвращает двоичное представление числа. Если число отрицательное то возвращает обратный код
     */
    private static String toBinaryString(byte number){
        String result = "";
        byte val = number;

        val = (val < 0) ? (byte) ~(-val) : val;

        for (byte i = 7; i >= 0; --i) {
            result += (val >> i & 1);
        }

        return result;
    }
}
