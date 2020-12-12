package com.gjxaiou.advanced.day02;

/**
 * @author GJXAIOU
 */
public class FindNewTypeChar {

    public static String pointNewChar(String s, int k) {
        if (s == null || s.equals("") || k < 0 || k >= s.length()) {
            return "";
        }

        char[] inputArray = s.toCharArray();
        // inputArray[k] 左边连续出现的大写字母数
        int upperNum = 0;
        for (int i = k - 1; i >= 0; i--) {
            // 遇到小写就停止寻找左边的大写字母个数
            if (!isUpper(inputArray[i])) {
                break;
            }
            upperNum++;
        }
        // 第一种情况：左边出现的大写字母为奇数
        if ((upperNum & 1) == 1) {
            //大写字母不能单独出现，所以一定和当前 k 字符共同组成新字符，即 [k-1,k]
            return s.substring(k - 1, k + 1);
        }

        // 第二种情况，左边大写偶数，当前 k 字符是大写
        if (isUpper(inputArray[k])) {
            // 大写字母不能单独出现，所以一定和后面的 k+1 字符共同组成新字符
            return s.substring(k, k + 2);
        }

        //第三种情况，左边大写偶数，当前k字符是小写，返回当前k字符即可
        return String.valueOf(inputArray[k]);
    }

    // 判断字符是否为大写
    public static boolean isUpper(char ch) {
        return !(ch < 'A' || ch > 'Z');
    }


    public static void main(String[] args) {
        String str = "aaABCDEcBCg";
        System.out.println(pointNewChar(str, 7));
        System.out.println(pointNewChar(str, 4));
        System.out.println(pointNewChar(str, 10));

    }
}
