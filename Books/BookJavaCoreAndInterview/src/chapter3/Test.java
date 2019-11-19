package chapter3;

/**
 * @author GJXAIOU
 * @create 2019-08-18-13:53
 */
public class Test {
    public static void main(String[] args) {
        // 输出原始数二进制
        int number = 10;
        System.out.println("十进制：" + number + " 二进制：" + Integer.toBinaryString(number));

        // 左移一位并输出
        number = number << 1;
        System.out.println("十进制：" + number + " 二进制：" + Integer.toBinaryString(number));

        // 右移一位并输出
        number = number >> 1;
        System.out.println("十进制：" + number + " 二进制：" + Integer.toBinaryString(number));
    }
}
/**
 * 十进制：10 二进制：1010
 * 十进制：20 二进制：10100
 * 十进制：10 二进制：1010
 */
