package com.practice2020.meituan;

import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/23 19:16
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[][] inputValue = new int[num][2];
        int i = 0;
        int maxRow = 0;
        int minRow = 0;
        int maxColumn = 0;
        int minColumn = 0;
        while (i < num) {
            inputValue[i][0] = scanner.nextInt();
            minRow = Math.min(minRow, inputValue[i][0]);
            maxRow = Math.max(maxRow, inputValue[i][0]);
            inputValue[i][1] = scanner.nextInt();
            minColumn = Math.min(minColumn, inputValue[i][1]);
            maxColumn = Math.max(maxColumn, inputValue[i][1]);
            i++;
        }
        int res = 0;
        for (int j = 0; j < inputValue.length; j++) {
            if (inputValue[j][0] > minRow && inputValue[j][0] < maxRow) {
                if (inputValue[j][1] > minColumn && inputValue[j][1] < maxColumn) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
