package array.medium;

import org.junit.jupiter.api.Test;

/**
 * @author GJXAIOU
 * @create 2020/04/12 13:54
 */
public class Offer47 {
    // 方法一：DFS
    // 这里没有限定只能访问一次,所以不必要使用 boolean[][] visited;
    public int maxValue(int[][] grid) {
        int cow = grid.length;
        int column = grid[0].length;

        return dfs(grid, 0, 0, cow, column, 0);
    }


    public static int dfs(int[][] grid, int i, int j, int cow, int column, int res) {
        if (i >= cow || j >= column) {
            return res;
        }
        res += grid[i][j];
        return Math.max(dfs(grid, i + 1, j, cow, column, res),
                dfs(grid, i, j + 1, cow, column, res));
        // 因为是 DFS，不是回溯，所有不用恢复
    }


    // 方法二：动态规划
    public int maxValue2(int[][] grid) {
        return dp(grid, 0, 0);
    }

    public int dp(int[][] grid, int i, int j) {
        // 为 DP 数组赋初始值，第一行和第一列
        int dp[][] = new int[grid.length + 1][grid[0].length + 1];
        dp[0][0] = grid[0][0];
        for (int k = 1; k < grid.length; k++) {
            dp[k][0] = dp[k - 1][0] + grid[k][0];
        }
        for (int m = 1; m < grid[0].length; m++) {
            dp[0][m] = dp[0][m - 1] + grid[0][m];
        }

        for (i = 1; i < grid.length; i++) {
            for (j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
