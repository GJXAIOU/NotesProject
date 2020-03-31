package com.gjxaiou.easy.day08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintAllSubsquences {
    // 递归版本一：
    // res 为上一级决策之后的字符串值
    public static void printAllSub(String inputString, int i, String res) {
        char[] str = inputString.toCharArray();
        // 如果到最后一个元素就不要递归了
        if (i == str.length) {
            System.out.println(res);
            return;
        }
        // 有两种决策，一种当前为空，就是将上一步决策接着往下扔，另一种就是加上当前字符串然后往下扔
        printAllSub(inputString, i + 1, res);
        printAllSub(inputString, i + 1, res + String.valueOf(str[i]));
    }


    // 方式二：
    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    public static void process(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process(chs, i + 1);
        chs[i] = tmp;
    }

//
//    // 方式三：不能使用
//    public static void function(String str) {
//        char[] chs = str.toCharArray();
//        process(chs, 0, new ArrayList<Character>());
//    }
//
//    public static void process(char[] chs, int i, List<Character> res) {
//        if (i == chs.length) {
//            printList(res);
//        }
//        List<Character> resKeep = copyList(res);
//        resKeep.add(chs[i]);
//        process(chs, i + 1, resKeep);
//        List<Character> resNoInclude = copyList(res);
//        process(chs, i + 1, resNoInclude);
//    }
//
//    public static void printList(List<Character> res) {
//        // ...;
//    }
//
//    public static List<Character> copyList(List<Character> list) {
//        return null;
//    }

    public static void main(String[] args) {
        String test = "abc";
        // 测试递归版本
        printAllSub(test, 0, "");
        System.out.println("----------------------");
        printAllSubsquence(test);
        System.out.println("----------------");
    }
}
