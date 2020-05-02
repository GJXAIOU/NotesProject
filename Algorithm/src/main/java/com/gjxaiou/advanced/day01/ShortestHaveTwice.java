package com.gjxaiou.advanced.day01;

/**
 * @author GJXAIOU
 */
public class ShortestHaveTwice {

    public static String answer(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] strArray = str.toCharArray();
        if (strArray.length == 1) {
            return str + str;
        }
        if (strArray.length == 2) {
            return strArray[0] == strArray[1] ? (str + String.valueOf(strArray[0])) : (str + str);
        }
        int endNext = endNextLength(strArray);
        return str + str.substring(endNext);
    }

    public static int endNextLength(char[] chas) {
        // 多求一位
        int[] next = new int[chas.length + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (chas[pos - 1] == chas[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next[next.length - 1];
    }

    public static void main(String[] args) {
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abcdabcd";
        System.out.println(answer(test4));

        String test5 = "abracadabra";
        System.out.println(answer(test5));
    }
}
