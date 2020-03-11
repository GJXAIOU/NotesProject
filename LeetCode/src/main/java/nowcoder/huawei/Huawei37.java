package nowcoder.huawei;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/3/11 22:15
 */
public class Huawei37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputValue = scanner.nextInt();
        if (inputValue < 0) {
            inputValue = Math.abs(inputValue);
        }

        // 方案一：使用 HashMap
        HashMap<Integer, Integer> resHashMap = new HashMap<>();
        StringBuilder resStringBuilder = new StringBuilder();
        while (inputValue != 0) {
            int current = inputValue % 10;
            if (!resHashMap.containsKey(current)) {
                resHashMap.put(current, 1);
                resStringBuilder.append(current);
            }
            inputValue /= 10;
        }
        System.out.println(Integer.parseInt(resStringBuilder.toString()));

        // 方法二：使用数组代替 HashMap，因为只要判断 0 ~ 9 这 10 个数值是否重复
        int[] reference = new int[10];
        StringBuilder resStringBuilder2 = new StringBuilder();
        while (inputValue != 0) {
            int current = inputValue % 10;
            if (reference[current] == 0) {
                resStringBuilder2.append(current);
                reference[current] = 1;
            }
            inputValue /= 10;
        }
        System.out.println(Integer.parseInt(resStringBuilder2.toString()));
    }
}
