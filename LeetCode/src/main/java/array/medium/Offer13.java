package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/11 21:20
 */
public class Offer13 {
    // 方法：DFS
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.visited = new boolean[m][n];
        return dfs(m, n, k, 0, 0, 0, 0);
    }

    public int dfs(int m, int n, int k, int i, int j, int sumI, int sumJ) {
        // 如果越界或者该元素访问过就返回
        if (i >= m || j >= n || k < sumI + sumJ || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(m, n, k, i + 1, j, (i + 1) % 10 != 0 ? sumI + 1 : sumI - 8, sumJ) + dfs(m, n, k, i,
                j + 1, sumI, (j + 1) % 10 != 0 ? sumJ + 1 : sumJ - 8);
    }

}
