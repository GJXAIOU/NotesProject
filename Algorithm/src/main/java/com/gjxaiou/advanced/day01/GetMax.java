package com.gjxaiou.advanced.day01;

public class GetMax {

    // 方法一：得到 a - b 的符号就可以知道大小（缺点：a - b 值可能出现溢出）
    public static int getMax1(int a, int b) {
        int c = a - b;
        // signC 表示 c 的符号
        int signC = sign(c);
        // 表示与 c 相反的符号
        int signRevC = flip(signC);
        // signC 和 signRev 中必有一个为 0
        return a * signC + b * signRevC;
    }

    /**
     * 二进制中符号位 0 表示正，1 表示负：若正数或 0 返回 1，负数则返回 0
     * 这里是有符号数，n >> 31 表示将所有位上值右移 31 位，最终结果就是最后一位放的是原来数的第一位（即符号位），
     * 然后前面所有高位补符号位，如果是正数，所有前面所有高位补 0，反之补 1。
     * 所以如果 n 为正数，(n >> 31) & 1 结果为 0，然后调用 flip 实现 n ^ 1 结果为 1；
     * 如果 n 为负数，(n >> 31) & 1 结果为 1，然后调用 flip 实现 n ^ 1 结果为 0.
     */
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    public static int flip(int n) {
        // 异或，二进制表示，相同输出 0，不同输出 1；
        return n ^ 1;
    }

    //方法二：
    public static int getMax2(int a, int b) {
        int res = a - b;
        //返回 a 的符号
        int signA = sign(a);
        //返回 b 的符号
        int signB = sign(b);
        //返回 c 的符号
        int signC = sign(res);

        /*
         * 如果a、b的符号相同(+,+)、(-,-)，difSab=0,returnA=signC;如果 sc 是 1,返回 a,否则返回 b
         * 如果a、b的符号不同(+,-)、(-,+)，disSab=1,returnA=signA;如果 sa 是 1,返回 a,否则返回 b
         */
        int difSab = signA ^ signB;
        int sameSab = flip(difSab);
        int returnA = difSab * signA + sameSab * signC;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    // 测试
    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax1(a, b)); // wrong answer because of overflow
        System.out.println(getMax2(a, b));
    }
}
