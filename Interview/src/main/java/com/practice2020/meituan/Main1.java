package com.practice2020.meituan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/16 18:48
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] score = new int[row][column];
        int i = 0;
        if (row == 0 || column == 0){
            System.out.println(0);
        }
        while (i < row) {
            for (int j = 0; j < column; j++) {
                score[i][j] = scanner.nextInt();
            }
            i++;
        }

        HashSet resSet = new HashSet();
        int lineNum = 0;
        int max = -1;
        // 按照列遍历，max 为每一列最大值，set 中为每列最大值对应的行号
        for (int j1 = 0; j1 < score[0].length; j1++) {
            for (int i1 = 0; i1 < score.length; i1++) {
                if (score[i1][j1] >= max) {
                    max = score[i1][j1];
                    lineNum = i1;
                }
            }
            resSet.add(lineNum);
            max = -1;
        }
        System.out.println(resSet.size());
    }
}
