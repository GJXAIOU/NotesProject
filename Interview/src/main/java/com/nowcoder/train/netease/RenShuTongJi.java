package com.nowcoder.train.netease;

import java.util.HashMap;
import java.util.Scanner;

/**
 * AC
 * @Author GJXAIOU
 * @Date 2020/4/7 16:12
 */
public class RenShuTongJi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int workNum = scanner.nextInt();
        int searchNum = scanner.nextInt();
        scanner.nextLine();
        String[] workMoneyString = scanner.nextLine().split(" ");
        // 保存要查询的员工工资序号
        int[] search = new int[searchNum];
        int i = 0;
        while (i < searchNum) {
            search[i] = scanner.nextInt();
            i++;
        }
        int[] workMoney = new int[workNum];
        for (int j = 0; j < workMoneyString.length; j++) {
            workMoney[j] = Integer.parseInt(workMoneyString[j]);
        }

        HashMap<Integer, Integer> workMoneyMap = new HashMap<>();
        for (int k = 0; k < workMoney.length; k++) {
            if (workMoneyMap.containsKey(workMoney[k])) {
                int oldValue = workMoneyMap.get(workMoney[k]);
                workMoneyMap.replace(workMoney[k], oldValue, oldValue + 1);
            } else {
                workMoneyMap.put(workMoney[k], 1);
            }
        }


        // 查询
        for (int i1 = 0; i1 < search.length; i1++) {
            if (!workMoneyMap.containsKey(search[i1])){
                System.out.println(0);
            }else {
                int res =  workMoneyMap.get(search[i1]);
                System.out.println(res);
            }
        }

    }
}
