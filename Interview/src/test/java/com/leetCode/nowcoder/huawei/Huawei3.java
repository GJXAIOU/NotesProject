package nowcoder.huawei;

import java.util.Scanner;

/**
 * 注意点：
 * 1.此题是输入字符，输出字符，所有直接输出，不需要 .append 之后然后 toString() 在输出；
 * 2.判断字符输入使用 .hasNext() 即可不必使用 .hasNextLine()
 *
 * @Author GJXAIOU
 * @Date 2020/3/10 20:23
 */
public class Huawei3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String inputString = scanner.nextLine();
            for (int i = inputString.length() - 1; i >= 0; i--) {
                System.out.print(inputString.charAt(i));
            }
        }
    }

}
