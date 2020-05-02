package nowcoder.huawei;

import java.util.Scanner;

/**
 * 思路：
 * 使用双指针：后指针在最后，然后前指针从最后依次向前遍历，如果遇到空格，则将该位置下一个元素到后指针位置元素加入结果中；
 * 然后后指针指向当前空格前一位。
 * 当遍历到 0 位置时候也要将最后一个结果输出；
 * <p>
 * 方法二：根据空格切割字符串，然后反序拼接
 *
 * @Author GJXAIOU
 * @Date 2020/3/10 20:54
 */
public class Huawei55 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String outputString = reverse(inputString);
        System.out.println(outputString);
    }

    // 方法一：
    public static String reverse(String sentence) {
        int length = sentence.length();
        int last = length;
        StringBuilder resStringBuilder = new StringBuilder();

        for (int i = sentence.length() - 2; i >= 0; i--) {
            if (sentence.charAt(i) == ' ' || i == 0) {
                if (i == 0) {
                    resStringBuilder.append(sentence, i, last).append(' ');
                } else {
                    resStringBuilder.append(sentence, i + 1, last).append(' ');
                }

                last = i;
            }
        }
        return resStringBuilder.toString();
    }

    // 方法二:切割然后反序拼接
    public static String reverse2(String sentence) {
        String[] temp = sentence.split(" ");
        StringBuilder resStringBuilder = new StringBuilder();
        for (int i = temp.length - 1; i > 0; i--) {
            resStringBuilder.append(temp[i]).append(" ");
        }
        // 单独加上第一个，因为末尾不能加上空格，所以不能放入循环
        resStringBuilder.append(temp[0]);
        return resStringBuilder.toString();
    }
}
