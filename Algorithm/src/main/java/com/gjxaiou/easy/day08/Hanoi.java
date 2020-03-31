package com.gjxaiou.easy.day08;

public class Hanoi {

    // 方式一：
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, n, "left", "mid", "right");
        }
    }

    public static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            // 将 1 ~ n - 1 移动到 help 上
            func(rest - 1, down - 1, from, to, help);
            // 将 n 移动到
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    // 方式二：递归版本，只有这一个函数
    // N 表示现在为 1~N 问题，同时 N 个都是停留在 From 上面
    public static void process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            // 从 from 做到 help 上，可以借助 to
            process(N - 1, from, help, to);
            // 挪动 N
            System.out.println("move " + N + " from " + from + " to " + to);
            // 挪回来
            process(N - 1, help, to, from);
        }
    }

//    public static void moveLeftToRight(int N) {
//        if (N == 1) {
//            System.out.println("move 1 from left to right");
//        }
//        moveLeftToMid(N - 1);
//        System.out.println("move " + N + "from left to right");
//        moveMidToRight(N - 1);
//    }
//
//    public static void moveRightToLeft(int N) {
//
//    }
//
//    public static void moveLeftToMid(int N) {
//        if (N == 1) {
//            System.out.println("move 1 from left to mid");
//        }
//        moveLeftToRight(N - 1);
//        System.out.println("move " + N + "from left to mid");
//        moveRightToMid(N - 1);
//    }
//
//    public static void moveMidToLeft(int N) {
//
//    }
//
//    public static void moveRightToMid(int N) {
//
//    }
//
//    public static void moveMidToRight(int N) {
//        if (N == 1) {
//            System.out.println("move 1 from mid to right");
//        }
//        moveMidToLeft(N - 1);
//        System.out.println("move " + N + "from mid to right");
//        moveLeftToRight(N - 1);
//    }

    public static void main(String[] args) {
        int n = 5;
        hanoi(n);

        process(5, "左", "右", "中");
    }
}
