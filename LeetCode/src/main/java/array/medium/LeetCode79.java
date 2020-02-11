package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/11 10:07
 */
public class LeetCode79 {
    // 表示当前位置字符是否已经被使用过
    public boolean[][] marked;
    // 表示逐步判断行走的方向
    public int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board == null) {
            return false;
        }

        marked = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int start) {
        // 如果已经遍历到字符串的最后一位，则就直接判断是否相同即可
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        // 如果字符串首字符和当前位置值相等，开始判断
        if (board[i][j] == word.charAt(start)) {
            // 标识该位置字符已经被遍历
            marked[i][j] = true;
            // 然后开始向该位置四周判断
            for (int k = 0; k < 4; k++) {
                int newRow = i + direction[k][0];
                int newColumn = j + direction[k][1];
                // 如果新位置没有被判断过，同时还表格中，则遍历
                boolean isExit =
                        (newRow >= 0 && newRow <= board.length - 1) && (newColumn >= 0 && newColumn <= board[0].length - 1);
                if (isExit && !marked[newRow][newColumn]) {
                    if (dfs(board, word, newRow, newColumn, start + 1)) {
                        return true;
                    }
                }
            }
            // 如果一直返回 true，则说明该位置没有被使用
            marked[i][j] = false;

        }
        return false;
    }
}
