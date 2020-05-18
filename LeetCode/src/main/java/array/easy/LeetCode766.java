package array.easy;

import java.util.HashMap;

/**
 * @author GJXAIOU
 * @create 2020/05/18 21:56
 */
public class LeetCode766 {
    // 方法一：直接比较
    public boolean isToeplitzMatrix(int[][] matrix) {
        //除了边缘，只要判读该数和其左斜上方数是否相等即可
        int cows = matrix.length;
        int colums = matrix[0].length;
        if (matrix == null) {
            return false;
        }
        for (int i = 0; i < cows; i++) {
            for (int j = 0; j < colums; j++) {
                if (i != 0 && j != 0) {
                    if (matrix[i][j] == matrix[i - 1][j - 1]) {
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 方法二：HashMap
    public boolean isToeplitzMatrix2(int[][] matrix) {
        HashMap tempMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int index = i - j;
                if (tempMap.containsKey(index)) {
                    if (matrix[i][j] != (int) tempMap.get(index)) {
                        return false;
                    }
                } else {
                    tempMap.put(index, matrix[i][j]);
                }
            }
        }
        return true;
    }
}
