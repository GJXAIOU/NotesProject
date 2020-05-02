package com.practice2020.netease;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/4/7 19:44
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNum = scanner.nextInt();
        int partyNum = scanner.nextInt();
        int initNum = scanner.nextInt();
        scanner.nextLine();
        int temp = partyNum;
        // 因为已经被感染的人会感染其他人
        HashMap initHashMap = new HashMap();
        initHashMap.put(initNum, 1);
        HashMap lineMap = new HashMap<>();
        HashMap ansMap = new HashMap();
        while (temp > 0) {
            String[] lineValue = scanner.nextLine().split(" ");
            int[] line = new int[Integer.parseInt(lineValue[0])];
            for (int i = 0; i < line.length; i++) {
                line[i] = Integer.parseInt(lineValue[i + 1]);
            }


            for (int i = 0; i < line.length; i++) {
                lineMap.put(line[i], 1);
            }
            for (Object o1 : initHashMap.keySet()) {
                // 如果每行中都包含 感染列表的人，结果中加上感染列表的人，最终结果也要加上
                if (lineMap.containsKey(o1)) {
                    for (Object o : lineMap.keySet()) {
                        ansMap.put(o, 1);
                        initHashMap.put(o, 1);
                    }
                    lineMap.clear();
                    break;
                }
            }

            temp--;
        }
        // System.out.println(ans - partyNum + 1);

        System.out.println(ansMap.size() > totalNum ? totalNum : ansMap.size());
    }
}
