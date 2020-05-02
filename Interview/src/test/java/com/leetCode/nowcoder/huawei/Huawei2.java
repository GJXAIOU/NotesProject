package nowcoder.huawei;

import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/3/11 22:48
 */
public class Huawei2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputValue = scanner.nextInt();

        int count = 0;
        while (inputValue != 0) {
            // 最后一位 & 1
            if ((inputValue & 1) == 1) {
                count++;
            }
            // 右移一位，用左边的一位再循环
            inputValue >>= 1;
        }
        System.out.println(count);
    }
}
