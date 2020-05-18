package array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 */
public class Offer12 {
    List<Boolean> res = new ArrayList<Boolean>();

    public boolean exist(char[][] board, String word) {
        boolean fills = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从任意位置开始尝试搜索
                StringBuilder path = new StringBuilder();
                boolean[][] used = new boolean[board.length][board[0].length];
                backTrack(board, word, used, i, j, path);
                Arrays.fill(used,fills);
            }
        }
        for (Boolean re : res) {
            if (re == true) {
                return true;
            }
        }
        return false;
    }

    public void backTrack(char[][] board, String word, boolean[][] used, int i, int j,
                          StringBuilder path) {

        // 结束条件
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || path.toString().length() == word.length()) {
            if (path.equals(word)) {
                res.add(true);
            } else {
                res.add(false);
            }
        }

        // 选择列表
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int m = 0; m < 4; m++) {
            int newX = i + direct[m][0];
            int newY = j + direct[m][1];
            if (newX < board.length && newX >= 0 && newY >=0 && newY < board[0].length && !used[newX][newY]) {
                used[newX][newY] = true;
                path.append(board[newX][newY]);
                backTrack(board, word, used, newX, newY, path);
                used[newX][newY] = false;
                path.deleteCharAt(path.toString().length() - 1);
            }
        }
    }
}
