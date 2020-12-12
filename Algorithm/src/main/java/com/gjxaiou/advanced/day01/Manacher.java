package com.gjxaiou.advanced.day01;

/**
 * @Author GJXAIOU
 * @Date 2020/1/1 14:22
 */
public class Manacher {
    // 将输入字符串构造前后和中间加上特殊符号，构成奇数长度。
    public static char[] inputStringToOddNum(String str) {
        char[] strArray = str.toCharArray();

        char[] resArray = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != resArray.length; i++) {
            resArray[i] = (i & 1) == 0 ? '#' : strArray[index++];
        }
        return resArray;
    }

    public static int maxLoopsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = inputStringToOddNum(str);
        // 回文半径数组
        int[] pArr = new int[charArr.length];
        // indexC 为对称中心 C
        int indexC = -1;
        int pR = -1;
        int maxResLength = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            /**
             *  i < pR,表示 i 在回文右边界里面
             *  2 * indexC - 1 就是对应 i' 位置
             *  i 只要在回文右边界里面，则 i 的回文半径 pArr[i] 为 i'的回文半径 或者 pR - i 两者中最小的一个。
             *  如果 i > pR 则只能暴力破解
             */
            pArr[i] = i < pR ? Math.min(pArr[2 * indexC - i], pR - i) : 1;
            // 四种情况都让扩一下，其中 1 和 4 会成功，但是 2 ，3 会失败则回文右边界不改变；可以自己写成 if-else 问题。
            while ((i + pArr[i] < charArr.length) && (i - pArr[i] > -1)) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                indexC = i;
            }
            maxResLength = Math.max(maxResLength, pArr[i]);
        }
        return maxResLength - 1;
    }

    public static void main(String[] args) {
        int length = maxLoopsLength("123abccbadbccba4w2");
        System.out.println(length);
    }
}
