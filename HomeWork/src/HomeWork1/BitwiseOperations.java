/*
 * Author: Vitali Tsvirko
 * Date: 2020.11.27
 * Description:
 * 	1.1. http://developer.alexanderklimov.ru/android/java/bitwise.php
 *	1.2 Использовать ВСЕ возможные побитовые операции с числами 42 и 15.
 *	1.3 Использовать ВСЕ возможные побитовые операции с числами -42 и -15.
 *	1.4 В комментариях к каждой операции написать двоичный код каждого числа и результата операции.
 *	1.5 Попробуйте сделать побитовую операцию с числова 42.5
 *
 *	Т.к. тип данных явно не указан считаем что приведенные в задании значения это литералы, т.е. иеем тип int.
 *
 */

package HomeWork1;

public class BitwiseOperations {

    public static void main(String[] args) {
        printAllBitwiseOperations( 42, 25);
        /* задание 1.2
        --- Single variable operations ---
        ~42 = -43 (Binary View: ~101010 = 11111111111111111111111111010101)
        ~25 = -26 (Binary View: ~11001 = 11111111111111111111111111100110)

        --- Two variable operations ---
        -- AND --
        42 & 25 = 8 (Binary View: 101010 & 11001 = 1000)
        ~42 & 25 = 17 (Binary View: ~101010 & 11001 = 10001)
        42 & ~25 = 34 (Binary View: 101010 & ~11001 = 100010)
        ~42 & ~25 = -60 (Binary View: ~101010 & ~11001 = 11111111111111111111111111000100)

        -- OR --
        42 | 25 = 59 (Binary View: 101010 | 11001 = 111011)
        ~42 | 25 = -35 (Binary View: ~101010 | 11001 = 11111111111111111111111111011101)
        42 | ~25 = -18 (Binary View: 101010 | ~11001 = 11111111111111111111111111101110)
        ~42 | ~25 = -9 (Binary View: ~101010 | ~11001 = 11111111111111111111111111110111)

        -- XOR --
        42 ^ 25 = 51 (Binary View: 101010 ^ 11001 = 110011)
        ~42 ^ 25 = -52 (Binary View: ~101010 ^ 11001 = 11111111111111111111111111001100)
        42 ^ ~25 = -52 (Binary View: 101010 ^ ~11001 = 11111111111111111111111111001100)
        ~42 ^ ~25 = 51 (Binary View: ~101010 ^ ~11001 = 110011)

        -- Shift left --
        42 << 25 = 1409286144 (Binary View: 101010 << 11001 = 1010100000000000000000000000000)
        ~42 << 25 = -1442840576 (Binary View: ~101010 << 11001 = 10101010000000000000000000000000)
        42 << ~25 = 2688 (Binary View: 101010 << ~11001 = 101010000000)
        ~42 << ~25 = -2752 (Binary View: ~101010 << ~11001 = 11111111111111111111010101000000)

        -- Shift right --
        42 >> 25 = 0 (Binary View: 101010 >> 11001 = 0)
        ~42 >> 25 = -1 (Binary View: ~101010 >> 11001 = 11111111111111111111111111111111)
        42 >> ~25 = 0 (Binary View: 101010 >> ~11001 = 0)
        ~42 >> ~25 = -1 (Binary View: ~101010 >> ~11001 = 11111111111111111111111111111111)

        -- Shift right with zero padding --
        42 >>> 25 = 0 (Binary View: 101010 >>> 11001 = 0)
        ~42 >>> 25 = 127 (Binary View: ~101010 >>> 11001 = 1111111)
        42 >>> ~25 = 0 (Binary View: 101010 >>> ~11001 = 0)
        ~42 >>> ~25 = 67108863 (Binary View: ~101010 >>> ~11001 = 11111111111111111111111111)
         */

        printAllBitwiseOperations( -42,-25);
        /* задание 1.3
                --- Single variable operations ---
        ~-42 = 41 (Binary View: ~11111111111111111111111111010110 = 101001)
        ~-25 = 24 (Binary View: ~11111111111111111111111111100111 = 11000)

        --- Two variable operations ---
        -- AND --
        -42 & -25 = -58 (Binary View: 11111111111111111111111111010110 & 11111111111111111111111111100111 = 11111111111111111111111111000110)
        ~-42 & -25 = 33 (Binary View: ~11111111111111111111111111010110 & 11111111111111111111111111100111 = 100001)
        -42 & ~-25 = 16 (Binary View: 11111111111111111111111111010110 & ~11111111111111111111111111100111 = 10000)
        ~-42 & ~-25 = 8 (Binary View: ~11111111111111111111111111010110 & ~11111111111111111111111111100111 = 1000)

        -- OR --
        -42 | -25 = -9 (Binary View: 11111111111111111111111111010110 | 11111111111111111111111111100111 = 11111111111111111111111111110111)
        ~-42 | -25 = -17 (Binary View: ~11111111111111111111111111010110 | 11111111111111111111111111100111 = 11111111111111111111111111101111)
        -42 | ~-25 = -34 (Binary View: 11111111111111111111111111010110 | ~11111111111111111111111111100111 = 11111111111111111111111111011110)
        ~-42 | ~-25 = 57 (Binary View: ~11111111111111111111111111010110 | ~11111111111111111111111111100111 = 111001)

        -- XOR --
        -42 ^ -25 = 49 (Binary View: 11111111111111111111111111010110 ^ 11111111111111111111111111100111 = 110001)
        ~-42 ^ -25 = -50 (Binary View: ~11111111111111111111111111010110 ^ 11111111111111111111111111100111 = 11111111111111111111111111001110)
        -42 ^ ~-25 = -50 (Binary View: 11111111111111111111111111010110 ^ ~11111111111111111111111111100111 = 11111111111111111111111111001110)
        ~-42 ^ ~-25 = 49 (Binary View: ~11111111111111111111111111010110 ^ ~11111111111111111111111111100111 = 110001)

        -- Shift left --
        -42 << -25 = -5376 (Binary View: 11111111111111111111111111010110 << 11111111111111111111111111100111 = 11111111111111111110101100000000)
        ~-42 << -25 = 5248 (Binary View: ~11111111111111111111111111010110 << 11111111111111111111111111100111 = 1010010000000)
        -42 << ~-25 = -704643072 (Binary View: 11111111111111111111111111010110 << ~11111111111111111111111111100111 = 11010110000000000000000000000000)
        ~-42 << ~-25 = 687865856 (Binary View: ~11111111111111111111111111010110 << ~11111111111111111111111111100111 = 101001000000000000000000000000)

        -- Shift right --
        -42 >> -25 = -1 (Binary View: 11111111111111111111111111010110 >> 11111111111111111111111111100111 = 11111111111111111111111111111111)
        ~-42 >> -25 = 0 (Binary View: ~11111111111111111111111111010110 >> 11111111111111111111111111100111 = 0)
        -42 >> ~-25 = -1 (Binary View: 11111111111111111111111111010110 >> ~11111111111111111111111111100111 = 11111111111111111111111111111111)
        ~-42 >> ~-25 = 0 (Binary View: ~11111111111111111111111111010110 >> ~11111111111111111111111111100111 = 0)

        -- Shift right with zero padding --
        -42 >>> -25 = 33554431 (Binary View: 11111111111111111111111111010110 >>> 11111111111111111111111111100111 = 1111111111111111111111111)
        ~-42 >>> -25 = 0 (Binary View: ~11111111111111111111111111010110 >>> 11111111111111111111111111100111 = 0)
        -42 >>> ~-25 = 255 (Binary View: 11111111111111111111111111010110 >>> ~11111111111111111111111111100111 = 11111111)
        ~-42 >>> ~-25 = 0 (Binary View: ~11111111111111111111111111010110 >>> ~11111111111111111111111111100111 = 0)
         */

        /* задание 1.5
            побитовые операторы применимы только к целочисленными типам
         */
    }

    private static void printAllBitwiseOperations (int a, int b){
        System.out.println("--- Single variable operations ---");
        System.out.println(convertVariableOperationToString(a,"~",~a));
        System.out.println(convertVariableOperationToString(b,"~",~b));
        System.out.println(" ");

        System.out.println("--- Two variable operations ---");
        System.out.println("-- AND -- ");
        System.out.println(covertExpressionToString(a, b, "", "&", "", (a & b)));
        System.out.println(covertExpressionToString(a, b, "~", "&", "", (~a & b)));
        System.out.println(covertExpressionToString(a, b, "", "&", "~", (a & ~b)));
        System.out.println(covertExpressionToString(a, b, "~", "&", "~", (~a & ~b)));
        System.out.println("");

        System.out.println("-- OR -- ");
        System.out.println(covertExpressionToString(a, b, "", "|", "", (a | b)));
        System.out.println(covertExpressionToString(a, b, "~", "|", "", (~a | b)));
        System.out.println(covertExpressionToString(a, b, "", "|", "~", (a | ~b)));
        System.out.println(covertExpressionToString(a, b, "~", "|", "~", (~a | ~b)));
        System.out.println("");

        System.out.println("-- XOR -- ");
        System.out.println(covertExpressionToString(a, b, "", "^", "", (a ^ b)));
        System.out.println(covertExpressionToString(a, b, "~", "^", "", (~a ^ b)));
        System.out.println(covertExpressionToString(a, b, "", "^", "~", (a ^ ~b)));
        System.out.println(covertExpressionToString(a, b, "~", "^", "~", (~a ^ ~b)));
        System.out.println("");

        System.out.println("-- Shift left -- ");
        System.out.println(covertExpressionToString(a, b, "", "<<", "", (a << b)));
        System.out.println(covertExpressionToString(a, b, "~", "<<", "", (~a << b)));
        System.out.println(covertExpressionToString(a, b, "", "<<", "~", (a << ~b)));
        System.out.println(covertExpressionToString(a, b, "~", "<<", "~", (~a << ~b)));
        System.out.println("");

        System.out.println("-- Shift right -- ");
        System.out.println(covertExpressionToString(a, b, "", ">>", "", (a >> b)));
        System.out.println(covertExpressionToString(a, b, "~", ">>", "", (~a >> b)));
        System.out.println(covertExpressionToString(a, b, "", ">>", "~", (a >> ~b)));
        System.out.println(covertExpressionToString(a, b, "~", ">>", "~", (~a >> ~b)));
        System.out.println("");

        System.out.println("-- Shift right with zero padding -- ");
        System.out.println(covertExpressionToString(a, b, "", ">>>", "", (a >>> b)));
        System.out.println(covertExpressionToString(a, b, "~", ">>>", "", (~a >>> b)));
        System.out.println(covertExpressionToString(a, b, "", ">>>", "~", (a >>> ~b)));
        System.out.println(covertExpressionToString(a, b, "~", ">>>", "~", (~a >>> ~b)));
    }

    private static String covertExpressionToString(int a, int b, String operand1, String operand2, String operand3, int result){
        return String.format("%s%s %s %s%s = %s (Binary View: %s%s %s %s%s = %s)", operand1, a, operand2, operand3, b, result, operand1, convertValueToStringBinaryView(a), operand2, operand3, convertValueToStringBinaryView(b), convertValueToStringBinaryView(result));
    }

    private static String convertVariableOperationToString (int a, String operand, int result){
        return String.format("%s%s = %s (Binary View: %s%s = %s)", operand, a, result, operand, convertValueToStringBinaryView(a), convertValueToStringBinaryView(result));
    }

    private static String convertValueToStringBinaryView (int a){
        return String.format("%s",Integer.toBinaryString(a)).replace(' ', '0');
    }
}
