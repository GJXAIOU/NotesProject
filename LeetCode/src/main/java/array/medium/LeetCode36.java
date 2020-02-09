package array.medium;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/9 14:26
 */
public class LeetCode36 {

    public boolean isValidSudoku(char[][] board) {
        // 使用三个 HashMap 数组，分别存储判重每行、每列、小数独的 HashMap
        HashMap<Integer, Integer>[] rowMap = new HashMap[9];
        HashMap<Integer, Integer>[] columnMap = new HashMap[9];
        HashMap<Integer, Integer>[] boxMap = new HashMap[9];
        // 每行、每列，每个小格子中元素都使用一个 HashMap 存储判重
        for (int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<Integer, Integer>();
            columnMap[i] = new HashMap<Integer, Integer>();
            boxMap[i] = new HashMap<Integer, Integer>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 只判断数值
                if (board[i][j] != '.') {
                    int num = (int) board[i][j];
                    int boxIndex = (i / 3) * 3 + j / 3;

                    // 元素判重
                    rowMap[i].put(num, rowMap[i].getOrDefault(num, 0) + 1);
                    columnMap[j].put(num, columnMap[j].getOrDefault(num, 0) + 1);
                    boxMap[boxIndex].put(num, boxMap[boxIndex].getOrDefault(num, 0) + 1);

                    // 如果该元素出现过则返回 false
                    if (rowMap[i].get(num) > 1 || columnMap[j].get(num) > 1 || boxMap[boxIndex].get(num) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
