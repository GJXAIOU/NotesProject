package com.practice2020.alibaba;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/20 19:06
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int n = scanner.nextInt();
        int[] value = new int[n];
        int i = 0;
        while (i < n) {
            value[i++] = scanner.nextInt();
        }
        int fight = fight(a, value);
        System.out.println(fight);
    }


    public static int fight(int enery, int[] value) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int goldNum = 0;
        // 先把能干掉的全部干掉
        for (int i = 0; i < value.length; i++) {
            if (enery >= value[i]) {
                goldNum++;
            } else {
                // 干不掉的全部放入 map 中
                if (map.get(value[i]) == null) {
                    map.put(value[i], 0);
                }
                map.put(value[i], map.get(value[i]) + 1);
            }
        }
        // 剩下都是打不过去的
        while (true) {
            // 用不用这个 coin
            boolean flag = true;
            int key = -1;
            for (int keyValue : map.keySet()) {
                // 获得的金币数大于花费的金币数 get
                if (keyValue - enery <= goldNum && map.get(keyValue) > keyValue - enery) {
                    flag = false;
                    key = keyValue;
                    // 现在的金币数
                    goldNum = goldNum - (keyValue - enery) + map.get(keyValue);
                    break;
                }
            }
            // 这些不打了
            if (key != -1) {
                map.remove(key);
            }
            if (flag) {
                break;
            }
        }

        return goldNum;
    }
}
