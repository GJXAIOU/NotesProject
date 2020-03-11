package nowcoder.huawei;

import java.util.Scanner;

/**
 * 思路：
 * 令 i = 2；然后 ulDataInput % i == 0,如果正确则 ulDataInput /= i；否则 i++，直到 i > ulDataInput
 *
 * @Author GJXAIOU
 * @Date 2020/3/11 20:13
 */
public class Huawei60 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            long inputValue = scanner.nextLong();
            if (inputValue < 2) {
                return;
            }
            System.out.println(getResult(inputValue));
        }
    }

    public static String getResult(long ulDataInput) {

        int i = 2;
        StringBuilder resStringBuilder = new StringBuilder();
        while (ulDataInput >= i) {
            if (ulDataInput % i == 0) {
                if (ulDataInput == i) {
                    resStringBuilder.append(i).append(' ');
                    break;
                } else {
                    resStringBuilder.append(i).append(' ');
                    ulDataInput /= i;
                }

            } else {
                i++;
            }
        }
        return resStringBuilder.toString();
    }
}
