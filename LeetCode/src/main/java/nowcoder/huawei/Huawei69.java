package nowcoder.huawei;

import java.util.Scanner;

/**
 * 思路：
 * 16进制到十进制
 * 16进制数的第0位的权值为16的0次方，第1位的权值为16的1次方，第2位的权值为16的2次方……
 * 所以，在第N（N从0开始）位上，如果是是数 X （X 大于等于0，并且X小于等于 15，即：F）表示的大小为 X * 16的N次方。
 * 例：2AF5换算成10进制:
 * 用竖式计算：
 * 第0位： 5 * 16^0 = 5
 * 第1位： F * 16^1 = 240
 * 第2位： A * 16^2= 2560
 * 第3位： 2 * 16^3 = 8192
 * -------------------------------------
 * 10997
 * 直接计算就是：
 * 5 * 16^0 + F * 16^1 + A * 16^2 + 2 * 16^3 = 10997
 * 1.首先删除前面的 0x，或者直接跳过前面两个字符；
 * <p>
 * <p>
 * 注意：
 * 1.多组同时输入
 * 2.输入输出均为字符串
 *
 * @Author GJXAIOU
 * @Date 2020/3/11 18:38
 */
public class Huawei69 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 输入字符串去除最前面的 0x
            String inputString = scanner.nextLine().substring(2);
            System.out.println(convert(inputString));
            // 方法二：一步解决
            // System.out.println(Integer.parseInt(inputString,16));
        }
    }

    public static String convert(String inputString) {

        if (inputString.length() == 0 || inputString == null) {
            return new String();
        }

        int resNum = 0;
        for (int i = 0; i <= inputString.length() - 1; i++) {
            if (inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'F') {
                // 因为 A 对应 65，相减得到 10
                resNum += Integer.valueOf(inputString.charAt(i) - 55) * Math.pow(16,
                        inputString.length() - 1 - i);
            } else {
                // 因为字符 0 的 ASCII 码为 48
                resNum += Integer.valueOf(inputString.charAt(i) - 48) * Math.pow(16,
                        inputString.length() - 1 - i);
            }
        }
        return String.valueOf(resNum);
    }
}
