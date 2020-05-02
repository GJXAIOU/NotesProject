package com.practice2020.baidu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 思路：分别求出求出所有可能性的最大公约数和最小公倍数
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = scanner.nextInt();
        int a = 0;
        int b = 0;
        ArrayList<Integer> ansList = new ArrayList<Integer>();
        int ans = 0;
        for (a = 1; a < max; a++) {
            for (b = 2; b > a; b++) {
                if (prime(a) && prime(b)){
                    ans = minCommonValue(a, b) - maxCommonValue(a, b);
                    ansList.add(ans);
                }
            }
        }

        int out = 0;
        for (Integer integer : ansList) {
            if (integer > out) {
                out = integer;
            }
        }
        System.out.println(out);
    }

    /**
     * 筛选出所有的素数
     *
     */
    public static boolean prime(int n){
        boolean isPrime  = true;
        if (n < 2){
            return false;
        }else {
            for (int i = 2; i <= Math.sqrt(n); i++){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * 递归求最大公约数
     *
     * @param a
     */
    public static int maxCommonValue(int a, int b) {
        // 默认 a > b,
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        // 如果能除尽则较小的一个就是最大公约数
        if (a % b == 0) {
            return b;
        } else {
            return maxCommonValue(b, a % b);
        }
    }

    /**
     * 使用上面的最大公约数求最小公倍数
     */
    public static int minCommonValue(int a, int b) {
        return a * b / maxCommonValue(a, b);
    }

}
