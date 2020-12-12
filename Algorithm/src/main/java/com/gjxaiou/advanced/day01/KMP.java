package com.gjxaiou.advanced.day01;

/**
 * @author GJXAIOU
 */
public class KMP {

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] strArray = str.toCharArray();
        char[] matchArray = match.toCharArray();
        int strIndex = 0;
        int matchIndex = 0;
        int[] next = getNextArray(matchArray);
        while ((strIndex < strArray.length) && (matchIndex < matchArray.length)) {
            if (strArray[strIndex] == matchArray[matchIndex]) {
                strIndex++;
                matchIndex++;
                // 数组中值等于 -1 ，说明是第一个元素，说明当前 str 中值连 match 第一个字母都匹配不上，则直接从 str 的下一个开始进行匹配
            } else if (next[matchIndex] == -1) {
                strIndex++;
            } else {
                matchIndex = next[matchIndex];
            }
        }
        return (matchIndex == matchArray.length) ? strIndex - matchIndex : -1;
    }

    /**
     * 求解 nextArr 方法
     */
    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[match.length];
        nextArr[0] = -1;
        nextArr[1] = 0;

        // 从第二个位置开始求值
        int curIndex = 2;
        int cn = 0;
        while (curIndex < nextArr.length) {
            // 如果 B 位置值等于 C 位置值，则 L + C = K + B
            if (match[curIndex - 1] == match[cn]) {
                // 则长度就是从 0 到 cn，即 ++cn 的长度
                nextArr[curIndex] = ++cn;
                curIndex++;
                // cn 换值，curIndex 不变，继续循环
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[curIndex++] = 0;
            }
        }
        return nextArr;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }
}
