package com.practice2020.huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/15 19:20
 */
public class Main2 {
    private static List<List<String>> resList = new LinkedList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            String aim = input[0];
            String value = input[1];
            String[] split = value.split("]");

            for (int i = 0; i < split.length; i++) {
                help(aim, split[i]);
            }
            for (List<String> strings : resList) {
                for (String string : strings) {
                    System.out.println(string + " ");
                }
                System.out.println();

            }
        }
    }


    private static void help(String aim, String value) {

        int begin = 0;
        int end = 0;
        for (int i = 0; i < value.length(); i++) {
            while (value.charAt(end) != '[') {
                end++;
            }
            // begin ~ end 之间为 aim 匹配值
            if (value.charAt(0) == ',') {
                begin++;
            }
            if (end - begin + 1 != aim.length()) {
                // 则不匹配，直接跳到下一个尝试
                return;
            }
        }
        for (int j = begin; j < end; j++) {
            if (aim.charAt(j) != value.charAt(j)) {
                return;
            }
        }

        // 如果匹配上了，end 位置就是 [ 前面一个字符,找最近的 ],下标为 K

        // 记录每一步的结果
        LinkedList<String> line = new LinkedList<>();
        StringBuilder tempLine = new StringBuilder();

        // [] 中间的字符串为
        String tempString = value.substring(end + 1);
        String[] split = tempString.split(",");
        for (int i1 = 0; i1 < split.length; i1++) {
            String temp = split[i1];
            for (int m = 0; m < temp.length(); m++) {
                if (temp.charAt(m) == '=') {
                    if (temp.substring(m + 1).startsWith("0x") || temp.substring(m + 1).startsWith("0X")) {
                        line.add(temp.substring(m + 1));
                    }
                }
            }
        }
        resList.add(line);
    }
}
