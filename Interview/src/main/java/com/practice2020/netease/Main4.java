package com.practice2020.netease;

import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/4/7 20:19
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        scanner.nextLine();
        // 构造数组，输入 n 行，每行 m 个
        int[] line = new int[m];
        String[] lineString = new String[m];
        int[][] inputValue = new int[n][m];
        int i = 0;
        int lineNum = 0;
        while (i < n){
            lineString = scanner.nextLine().split(" ");
            for (int i1 = 0; i1 < lineString.length; i1++) {
                line[i1] = Integer.parseInt(lineString[i1]);
            }
            inputValue[lineNum++] = line;
            i++;
        }

        int res[][] = distance(inputValue);
        for (int i1 = 0; i1 < res.length; i1++) {
            for (int j = 0;j < res[0].length; j++){
                System.out.println(res[i1][j]);
            }
        }


    }
    public static int[][] distance(int[][] inputValue){
        int column = inputValue[0].length;
        int row = inputValue.length;
        int[][] distances = new int[row][column];
        int distance = -1;
        int maxLength = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (inputValue[i][j] == 0){
                    continue;
                }
                maxLength = Math.max(i + j, row -1 -i+ column -1-j);
                maxLength = Math.max(row -1-i +j, column -1-j +i)> maxLength ?
                        Math.max(row - 1 -i + j, column -1-j + i) : maxLength;
                breakTo:for (int length = 1; length < maxLength; length++){
                    for (int i1 = 0; i1 <= length; i1++){
                        for (int j1 = 0; j1 <= length;j1++){
                            if (i1 + j1 == length){
                                if (cross(i + i1, j + j1, row, column) && inputValue[i1 + i][j + j1] == 0){
                                    distances[i][j] = length;
                                    break breakTo;
                                }
                                if (cross(i + i1, j - j1, row, column) && inputValue[i1 + i][j - j1] == 0){
                                    distances[i][j] = length;
                                    break breakTo;
                                }
                                if (cross(i - i1, j + j1, row, column) && inputValue[i1 - i][j + j1] == 0){
                                    distances[i][j] = length;
                                    break breakTo;
                                }
                                if (cross(i - i1, j - j1, row, column) && inputValue[i1 - i][j - j1] == 0){
                                    distances[i][j] = length;
                                    break breakTo;
                                }
                            }
                        }
                    }
                }
            }

        }
        return distances;
    }
 // 判断越界
    public static  boolean cross(int i, int j, int row, int column){
        if (i < 0 || j < 0 || i > row - 1 || j > column -1 ){
            return false;
        }
        return true;
    }
}
