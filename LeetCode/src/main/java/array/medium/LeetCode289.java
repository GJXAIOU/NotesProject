package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/8 16:58
 */
public class LeetCode289 {
    public void gameOfLife(int[][] board) {
        // 标记数组中每个值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = checkLoc(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
            }
        }
    }

    public int checkLoc(int[][] board, int i, int j) {
        int count = 0;
        // 左右上下边界：上下左右坐标值或者为边界，构建该元素的九宫格
        int left = Math.max(j - 1, 0);
        int right = Math.min(j + 1, board[i].length - 1);
        int top = Math.max(i - 1, 0);
        int bottom = Math.min(i + 1, board.length - 1);

        for (int x = top; x <= bottom; x++) {
            for (int y = left; y <= right; y++) {
                count = board[x][y] == 1 || board[x][y] == -1 ? count + 1 : count;
            }
        }
        // 如果当前值正好也是 1，count == 3/4 则表示该值周围是两个或者三个 1，如果是返回 1，反之返回小于两个或者大于三个则死亡，赋值为： -1，
        //如果该值为不为 1，如果总数为三则赋值为 -2，表示复活
        return board[i][j] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
    }

}
