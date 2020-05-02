package com.practice2020.kuaishou;

import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/12 16:08
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        int k = 0;
        StringBuilder inputTrueStr = new StringBuilder();
        while (k < inputStr.length()) {
            if (inputStr.charAt(k) == '(' || inputStr.charAt(k) == ')') {
                inputTrueStr.append(inputStr.charAt(k));

            }
            k++;
        }
        String str = inputTrueStr.toString();

        char[] strArray = str.toCharArray();
        int[] dp = new int[strArray.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && strArray[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }

        // 求原来 （ 和 ）个数
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i] == '(') {
                sumLeft++;
            }
            if (strArray[i] == ')') {
                sumRight++;
            }
        }
        // 匹配数目
        int pipei = res / 2;
        int left = sumLeft - res / 2;
        int right = sumRight - res / 2;
        System.out.println(pipei + " " + left + " " + right);
    }
}
