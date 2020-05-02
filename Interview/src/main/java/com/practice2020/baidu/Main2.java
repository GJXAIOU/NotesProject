package com.practice2020.baidu;

import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/3/29 20:12
 *
 * 注意点：long，不要用 int
 */
public class Main2 {
    public static void main(String[] args) {
        // 获取输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int maxValue = 50;
        long[] inputValue = new long[maxValue];
        for (int i = 0; i < n; i++) {
            long temp = scanner.nextLong();
            inputValue[i] = temp;
        }


        long ans = 0L;
        // 使用 flag 判断是否符合
        boolean flag = true;
        // 计数
        long[] counter = new long[maxValue + 10];
        while (flag) {
            flag = false;
          long  totalValue = 0L;
            for (int i = 0; i < n; i++) {
                counter[i] = inputValue[i] / n;
                inputValue[i] = inputValue[i] % n;
                totalValue += counter[i];
            }
            ans += totalValue;
            for (int j = 0 ; j < n; j++) {
                inputValue[j] += totalValue - counter[j];
                if (inputValue[j] >= n) {
                    flag = true;
                }
            }
            System.out.println(ans);
        }
    }
}
