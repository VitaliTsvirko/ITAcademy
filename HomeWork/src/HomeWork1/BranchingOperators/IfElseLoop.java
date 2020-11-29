/*
 * Author: Vitali Tsvirko
 * Date: 2020.11.29
 * Description:
 * Разбираемся с операторами ветвления. Используя статью https://metanit.com/java/tutorial/2.9.php запросить
у пользователя его имя. В данном задании у вас должно получиться 3 класса.
	Что в итоге надо сделать (общее условие):
		1. Если имя будет равно имени "Вася" тогда в консоль должно вывести сообщение "Привет!" на одной строке,
		а на второй "Я тебя так долго ждал".
		2. Если имя будет равно имени "Анастасия" тогда в консоль должно вывести сообщение "Я тебя так долго ждал".
		3. В случае если это будет другое имя то вывести сообщение "Добрый день, а вы кто?".
	Как это сделать:
		5.1 Написать в отдельном классе в отдельном main код который будет выполнять общее условие при помощи if
 *
 */

package HomeWork1.BranchingOperators;

import java.util.Objects;
import java.util.Scanner;

public class IfElseLoop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.nextLine();
        in.close();

        if (Objects.equals(name, "Вася")) {
            System.out.println("Привет!\nЯ тебя так долго ждал");
        } else
            if ((Objects.equals(name, "Анастасия"))) {
                System.out.println("Я тебя так долго ждал");
            } else {
            System.out.println("Добрый день, а вы кто?");
        }
    }
}
