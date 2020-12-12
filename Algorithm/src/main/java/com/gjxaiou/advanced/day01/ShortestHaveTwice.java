package com.gjxaiou.advanced.day01;

/**
 * @author GJXAIOU
 */
public class ShortestHaveTwice {

    public static String answer(String inputStr) {
        if (inputStr == null || inputStr.length() == 0) {
            return "";
        }
        char[] inputArray = inputStr.toCharArray();
        if (inputArray.length == 1) {
            return inputStr + inputStr;
        }
        if (inputArray.length == 2) {
            return inputArray[0] == inputArray[1] ? (inputStr + inputArray[0]) :
                    (inputStr + inputStr);
        }
        // 获取长度
        int endNextArrLength = endNextLength(inputArray);
        return inputStr + inputStr.substring(endNextArrLength);
    }

    public static int endNextLength(char[] inputArray) {
        // 多求一位
        int[] nextArr = new int[inputArray.length + 1];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int curIndex = 2;
        int cn = 0;
        while (curIndex < nextArr.length) {
            if (inputArray[curIndex - 1] == inputArray[cn]) {
                nextArr[curIndex++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[curIndex++] = 0;
            }
        }
        return nextArr[nextArr.length - 1];
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
