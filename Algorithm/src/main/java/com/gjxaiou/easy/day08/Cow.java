package com.gjxaiou.easy.day08;

public class Cow {
    // 方法一：从上而下递归
    public static int cowNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }

    // 方法二：从下到上的递推
    public static int cowNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int preRes = 3;
        int prePre = 2;
        int prePrePre = 1;
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 4; i <= n; i++) {
            temp1 = preRes;
            temp2 = prePre;
            // 当前值等于 f(n - 1) 即 preRes + f(n - 3) 即 prePrePre
            preRes += prePrePre;
            // f(n - 2) 值向右移动一个变成了 f(n - 1)，即 temp1 保存的 preRes 值。
            prePre = temp1;
            // f(n - 3) 值向右移动一个变成了 f(n - 2)，即 temp2 保存的 prePrePre 值。
            prePrePre = temp2;
        }
        return preRes;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
    }

}
