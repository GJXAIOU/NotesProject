package nowcoder.huawei;

import java.util.Scanner;

/**
 * *思路：
 * 1.创建一个 StringBuilder 作为返回结果；
 * 2.不断通过运算获取最后一位，然后加入到结果中；
 *
 * 注意点：
 * 1.所有方法加上 static，便于直接调用
 * 2.注意输入写法
 * 3.默认输出就是打印即可
 * @Author GJXAIOU
 * @Date 2020/3/10 19:23
 */
public class Huawei1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputValue = scanner.nextInt();
        System.out.println(reverse(inputValue));
    }

    public static String reverse(int inputValue) {
        StringBuilder resStringBuilder = new StringBuilder();
        String inputString = String.valueOf(inputValue);
        for (int i = 0; i < inputString.length(); i++) {
            int temp = inputValue % 10;
            inputValue /= 10;
            resStringBuilder.append(temp);
        }
        // 方法二：直接使用 String 的 CharAt(i)
//        for (int i = inputString.length() - 1; i >= 0; i--){
//            resStringBuilder.append(inputString.charAt(i));
//        }
        return resStringBuilder.toString();
    }
}
