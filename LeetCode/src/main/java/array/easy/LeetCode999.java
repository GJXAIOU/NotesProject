package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:51
 */
public class LeetCode999 {
    public int numRookCaptures(char[][] board) {
        //首先得到车的位置
        int xR = 0, yR = 0;
        int N = board.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'R') {
                    xR = i;
                    yR = j;
                    break;
                }
            }
        }

        //使车可以上下左右移动
        int[][] num = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int x = xR + num[i][0];
            int y = yR + num[i][1];

            while (x > 0 && x < 8 && y > 0 && y < 8) {
                if (board[x][y] != '.') {
                    if (board[x][y] == 'p') {
                        count++;
                    }
                    break;
                } else {
                    x += num[i][0];
                    y += num[i][1];
                }
            }
        }

        return count;
    }
}
