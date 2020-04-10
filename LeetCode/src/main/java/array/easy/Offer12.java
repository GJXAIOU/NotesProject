package array.easy;

/**
 * 比较典型的回溯
 */
public class Offer12 {
    // DFS
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k])
            return false;
        if (k == word.length - 1) {
            return true;
        }
        char tmp = board[i][j];
        board[i][j] = '0';

        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean res = false;
        for (int m = 0; m < 4; m++) {
            int newX = i + direct[m][0];
            int newY = j + direct[m][1];
            // 只要有一个方向的 dfs 结果为 true 则结果正确
            res = res || dfs(board, word, newX, newY, k + 1);
        }

        board[i][j] = tmp;
        return res;
    }
}
