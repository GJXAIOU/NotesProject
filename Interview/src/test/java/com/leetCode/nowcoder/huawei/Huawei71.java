package nowcoder.huawei;

import java.util.Scanner;

/**
 * 同上面的题目，注意 i == 0 的截至条件
 * @Author GJXAIOU
 * @Date 2020/3/10 21:50
 */
public class Huawei71 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        // 方法一：
        for (int i = inputString.length() - 2; i >= 0; i--) {
            if ((inputString.charAt(i) == ' ') || i == 0) {
                if (i == 0) {
                    System.out.println(inputString.length() - 1 - i + 1);
                } else {
                    System.out.println(inputString.length() - 1 - i);
                }
                break;
            }
        }


        // 方法二：使用 Java 自带方法
        System.out.println(inputString.length() - 1 - inputString.lastIndexOf(" "));
    }

}
