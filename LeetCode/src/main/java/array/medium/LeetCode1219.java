package array.medium;


/**
 * @author GJXAIOU
 * @create 2020/05/07 14:51
 */
public class LeetCode1219 {

    public int getMaximumGold(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, backTrack(grid, i, j));
                }
            }
        }
        return ans;
    }

    int[][] move = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private int backTrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int tempValue = grid[i][j];
        grid[i][j] = 0;

        int ret = 0;
        for (int k = 0; k < 4; k++) {
            int newX = i + move[k][0];
            int newY = j + move[k][1];
            ret = Math.max(ret, tempValue + backTrack(grid, newX, newY));
        }
        grid[i][j] = tempValue;
        return ret;
    }
}