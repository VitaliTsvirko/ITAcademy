package HomeWork3.task9;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *  <p>Данный класс выполняет расчет математического выражения заданного в строке</p>
 *  <p>Для расчет выражения используется преобразование строки с математическим выражением в Обратную польскую запись</p>
 *
 *  <p>Поддерживаются следующие операторы:</p>
 *  <ul>
 *  <li> ^ - возведение в степень </li>
 *  <li> * - умножение </li>
 *  <li> / - деление </li>
 *  <li> + - сложение </li>
 *  <li> - - вычитание </li>
 *  <li> | - модуль числа</li>
 *  <li> () - скобки для приоритетов  </li>
 *  </ul>
 *
 *  <p>Поддерживаются следующие константы:</p>
 *  <ul>
 *  <li>PI - число PI </li>
 *  <li>E - экспонента </li>
 *  </ul>
 *
 * @author Vitali Tsvirko
 */
public class CalculatorStringExpression {
    private List<String> reversePolishNotation = new LinkedList<>();

    /**
     * Данный метод выполняет расчет математического выражения заданного в строке
     *
     * <p>Поддерживаются следующие операторы:</p>
     * <ul>
     * <li> ^ - возведение в степень </li>
     * <li> * - умножение </li>
     * <li> / - деление </li>
     * <li> + - сложение </li>
     * <li> - - вычитание </li>
     * <li> | - модуль числа</li>
     * <li> () - скобки для приоритетов  </li>
     * </ul>
     *
     * <p>Поддерживаются следующие константы:</p>
     * <ul>
     * <li>PI - число PI </li>
     * <li>E - экспонента </li>
     * </ul>
     *
     * @param inputExpression строка с математическим выражением
     * @return результат вычисления математического выражения
     */
    public double calculateStringExpression(String inputExpression ){
        Deque<Double> stack = new ArrayDeque<>();

        this.reversePolishNotation.clear();

        transformToReversePolishNotation(inputExpression);

        if (this.reversePolishNotation == null || this.reversePolishNotation.isEmpty()){
            throw new IllegalStateException("Empty Reverse Polish Notation List");
        }

        for (String item : reversePolishNotation) {
             //Текущий элемент это число
            if (item.matches("[0-9]*\\.?[0-9]*")) {
                //symbol is digit
                stack.addLast(Double.parseDouble(item));
                continue;
            }

             //Текущий элемент это константа PI
            if (item.equals("PI")){
                stack.addLast(Math.PI);
                continue;
            }

             //Текущий элемент это константа E
            if (item.equals("E")){
                stack.addLast(Math.E);
                continue;
            }

            /*
             * Текущий элемент это операнд
             */
            if (stack.isEmpty()){
                throw new IllegalStateException("Calculation error. Empty stack");
            }

            //Вычисление выражения
            Double operand2 = stack.pollLast();
            Double operand1 = stack.isEmpty() || item.equals("|") ? 0 : stack.pollLast();

            stack.addLast(calculateExpression(item, operand1, operand2));
        }

        if (stack.size() != 1){
            throw new IllegalStateException("Calculation error. Syntax error in expression");
        }

        return stack.pollLast();
    }

    /**
     * Данным метод возвращает список строк содержащих математическое выражение в Обратной польской записи
     */
    public List<String> getReversePolishNotation() {
        return reversePolishNotation;
    }

    /**
     * Данные метод преобразует строку с математическим выражением в Обратную польскую запись
     * Полученная запись помещается в поле reversePolishNotation
     * Пример: 3 + 4 - 1 / 2 => [3, 4, +, 1, 2, /, -]
     * @param inputExpression строка с математическим выражением
     */
    private void transformToReversePolishNotation(String inputExpression){
        StringBuilder operand = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        String expression;
        //Проверка строки с математическим выражением
        expression = checkInputExpression(inputExpression);

        //Итерация по символам математического выражения
        boolean isFirstAbsSymbol = true; //Флаг первого символа модуля в выражении
        for (char currentSymbol : expression.toCharArray()) {
            /*
            * Текущий символ это число
            */
            if (Character.isDigit(currentSymbol) || currentSymbol == '.') {
                operand.append(currentSymbol);
                continue;   //Переходим к следующему символу выражения
            }

            /*
             * Текущий символ это буква (константа PI или E)
             */
            if (Character.isLetter(currentSymbol)){
                operand.append(currentSymbol);
                continue;   //Переходим к следующему символу выражения
            }

            /*
             * Текущий символ это операнд
             */
            //Добавляем число или константу в список и очищаем operand
            if (operand.length() != 0){
                reversePolishNotation.add(operand.toString());
                operand = new StringBuilder();
            }

            /*Работа со стеком операндов*/

            //Обработка символа модуля числя
            if (currentSymbol == '|'){
                if (isFirstAbsSymbol){
                    stack.addLast('[');     //Помещаем в стек [, который является маркером начала модуля
                    isFirstAbsSymbol = false;
                }
                else {
                    moveFromStackToList(stack, '[');   //Перемещаем из стека в List
                    stack.pollLast();                                  //Удаляем из стека '['
                    isFirstAbsSymbol = true;
                    stack.addLast('|');                             //Помещаем в стек |, которы является операндом модуля для калькулятора
                }
                continue;   //Переходим к следующему символу выражения
            }

            //Обработка скобок
            if (currentSymbol == '(') {
                stack.addLast(currentSymbol);
                continue;   //Переходим к следующему символу выражения
            }

            if (currentSymbol == ')'){
                moveFromStackToList(stack, '(');     //Перемещаем из стека в List
                stack.pollLast();                                   //Удаляем из стека '('
                continue;                                           //Переходим к следующему символу выражения
            }

            //Работа с приоритетом операций
            if (!stack.isEmpty() && getOperationSignPriority(stack.peekLast()) >= getOperationSignPriority(currentSymbol)) {
                moveFromStackToList(stack, '(');    //Перемещаем из стека в List
            }

            stack.addLast(currentSymbol);   //Помещаем в стек текущий операнд выражения
        }

        /*
         * Последний числовой символ выражения
         */
        if (operand.length() != 0){
            // Последний символ выражения это число
            reversePolishNotation.add(operand.toString());
        }

        //Помещаем в List то что осталось в стеке
        if (!stack.isEmpty()){
            moveFromStackToList(stack, '(');
        }
    }


    /**
     * Данный метод проверяет корректность выражения
     * Выполняется проверка:
     * 1) на пустое выражение
     * 2) правильное количество скобок
     * @param expression строка с математическим выражением
     * @return строка с математическим выражением без пробелов
     * @throws IllegalStateException
     *         Empty expression,
     *         Open and Close Bracket count is different,
     *         Open and Close ABS symbol count is different.
     */
    private String checkInputExpression(String expression){
        int openBracketCount = 0;
        int closeBracketCount = 0;
        int absBracketCount = 0;

        if (expression == null || expression.isEmpty()){
            throw new IllegalStateException("Empty expression");
        }

        //Убираем все пробелы из выражения
        expression = expression.replace(" ", "");

        //Проверяем начало выражения
        if (!(expression.matches("^(PI|E|\\d|\\-|\\(|\\|).*"))){
            throw new IllegalStateException("Expression begins with wrong character");
        }

        //Проверяем конец выражения
        if (!(expression.matches(".*(PI|E|\\d|\\-|\\)|\\|)$"))){
            throw new IllegalStateException("Expression ends with wrong character");
        }

        /*Проверка скобок*/
        //Подсчет скобок
        for (char s: expression.toCharArray()) {
            if (s == '(') ++openBracketCount;
            if (s == ')') ++closeBracketCount;
            if (s == '|') ++absBracketCount;
        }

         if (openBracketCount != closeBracketCount){
             throw new IllegalStateException("Open and Close Bracket count is different");
         }

        if (absBracketCount % 2 != 0){
            throw new IllegalStateException("Open and Close ABS symbol count is different");
         }

        return expression;
    }


    /**
     * Данный метод возвращает приоритет математической операции
     * @param symbol символ математической операции
     * @return приоритет математической операции.
     *   <p> ^ (степень)     = 4 </p>
     *   <p> * (умножение)   = 3 </p>
     *   <p> / (деление)     = 3 </p>
     *   <p> + (сложение)    = 2 </p>
     *   <p> - (вычитание)   = 2 </p>
     *   <p> ( (скобка)      = 1 </p>
     *   <p> | (модуль)      = 1 </p>
     *   <p> ) (скобка)      = -1 </p>
     * @throws IllegalStateException Illegal operation symbol. Возникает если
     *          в качестве арифметическая операция использован неверный символ
     */
    private int getOperationSignPriority(char symbol){
        if (symbol == '^'){
            return 4;
        }
        if (symbol == '*' || symbol == '/'){
            return 3;
        }
        if (symbol == '+' || symbol == '-'){
            return 2;
        }
        if (symbol == '(' || symbol == '[' || symbol == '|'){
            return 1;
        }
        if (symbol == ')'){
            return -1;
        }

        throw new IllegalStateException("Illegal operation symbol");
    }


    /**
     * Данные метод перемещает элементы из стека в список reversePolishNotation
     * Перемещение выполняется пока стек не пуст или пока в стеке не встретится символ открывающейся скобки,
     * который был передан в переменной openBracketSymbol.
     * @param stack стэк (LIFO)
     * @param openBracketSymbol символ открывающейся скобки до которого будет производиться перемещение
     */
    private void moveFromStackToList(Deque<Character> stack, char openBracketSymbol){
        while (!stack.isEmpty() && stack.peekLast() != openBracketSymbol){
            reversePolishNotation.add(String.valueOf(stack.pollLast()));
        }
    }


    /**
     * Данный метод выполняет расчет арифметических операций с двумя операндами
     * <p>Поддерживаются следующие операторы:</p>
     * <ul>
     *  <li> ^ - возведение в степень </li>
     *  <li> * - умножение </li>
     *  <li> / - деление </li>
     *  <li> + - сложение </li>
     *  <li> - - вычитание </li>
     *  <li> | - модуль числа</li>
     * </ul>
     * @param operationSign символ математической операции
     * @param operand1 первое число
     * @param operand2 второе число
     * @return возвращает результат выполнения арифметической операции.
     *         Если символ математической операции не верный вызывает исключение
     * @throws IllegalStateException Illegal operation sign in stack
     */
    private Double calculateExpression(String operationSign, Double operand1, Double operand2){
        switch (operationSign) {
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0){
                    throw new ArithmeticException("Calculation error. Division by zero");
                }
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "^":
                return Math.pow(operand1, operand2);
            case "|":
                return Math.abs(operand2);
            default:
                throw new IllegalStateException("Calculation error. Illegal operation sign in stack");
        }
    }

}
