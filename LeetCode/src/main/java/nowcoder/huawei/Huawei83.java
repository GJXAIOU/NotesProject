package nowcoder.huawei;

import java.util.Scanner;

/**
 * 注意：
 * 1.因为不区分大小写，所以直接在输入的时候全部转换为小写
 * 2.没有输入 char，使用 .nextLine().charAt(0) 得到；
 * 3.字符串.equals(字符串) , ' ' == 字符串
 *
 * @Author GJXAIOU
 * @Date 2020/3/10 22:09
 */
public class Huawei83 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine().toLowerCase();
        char inputChar = scanner.nextLine().toLowerCase().charAt(0);


        if (inputString == null || inputString.length() == 0) {
            System.out.println(0);
        }
        int count = 0;
        // 遍历数组计数即可
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.charAt(i) == inputChar) {
                count++;
            }
        }
        System.out.println(count);
    }
}
