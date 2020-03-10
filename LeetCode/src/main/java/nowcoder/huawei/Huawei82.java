package nowcoder.huawei;

import java.util.Scanner;

/**
 * 注意
 * subString 使用
 * @Author GJXAIOU
 * @Date 2020/3/10 22:56
 */
public class Huawei82 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            split(inputString);
        }
    }

    // 按 8 位切割字符串，不够补 0
    public static void split(String inputString) {
        while (inputString.length() >= 8) {
            System.out.println(inputString.substring(0, 8));
           inputString = inputString.substring(8);
        }
        if (inputString.length() > 0) {
            inputString += "00000000";
            System.out.println(inputString.substring(0,8));
        }
    }

}
