package com.gjxaiou.easy.day08;

import java.util.HashSet;

/**
 * 打印一个字符串的全排列（去重）
 */
public class PrintAllPermutations {

    // 方案一：全排列（不去重）
    public static void printAllPermutations1(String str) {
        char[] charArray = str.toCharArray();
        process1(charArray, 0);
    }

    public static void process1(char[] charArray, int local) {
        if (local == charArray.length) {
            System.out.println(String.valueOf(charArray));
        }
        for (int j = local; j < charArray.length; j++) {
            // 将第一个字符与后面的字符交换
            swap(charArray, local, j);
            // 对后面的所有字符进行全排列
            process1(charArray, local + 1);
            // 再将原来交换的字符交换回来，以便第一个字符在与其他字符交换
            swap(charArray, local, j);
        }
    }

    // 方法二：全排列去重
    public static void printAllPermutations2(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0);
    }

    public static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process2(chs, i + 1);
                swap(chs, i, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        printAllPermutations1(test2);
        System.out.println("======");
        printAllPermutations2(test2);
        System.out.println("======");
    }

}

