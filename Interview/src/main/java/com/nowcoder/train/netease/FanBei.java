package com.nowcoder.train.netease;

import java.util.Scanner;

/**
 * AC
 * @Author GJXAIOU
 * @Date 2020/4/7 15:14
 */
public class FanBei {
    //方案一：递归
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lineNum = scanner.nextInt();
        scanner.nextLine();
        while (lineNum > 0) {
            String[] inputLineValue = scanner.nextLine().split(" ");
            long a = Long.parseLong(inputLineValue[0]);
            long b = Long.parseLong(inputLineValue[1]);
            long p = Long.parseLong(inputLineValue[2]);
            long q = Long.parseLong(inputLineValue[3]);
            int res = count(a, b, p, q, 0);
            System.out.println(res);
            lineNum--;
        }
    }

    // 递归
    // 每一步判断分为 A+P,中间 p+ q 或者 P 不加 q
    private static int count(long A, long B, long p, long q, int num) {
        if (B <= A + p) {
            return num + 1;
        } else if (B <= A + p * q) {
            return num + 2;
        } else {
            return count(A, B, p * q * q, q, num + 2);
        }
    }
}
