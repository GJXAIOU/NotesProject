package array.medium;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author GJXAIOU
 * @create 2020/04/11 21:20
 */
public class Offer13 {

    // 方法：DFS
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        return dfs(m, n, k, 0, 0, 0, 0);
    }

    public int dfs(int m, int n, int k, int i, int j, int sumI, int sumJ) {
        // 如果越界或者该元素访问过就返回
        if (i >= m || j >= n || k < sumI + sumJ || visited[i][j]) {
            return 0;
        }
        // 做决定
        visited[i][j] = true;
        // 向下走和向右走决策
        return 1 + dfs(m, n, k, i + 1, j, (i + 1) % 10 != 0 ? sumI + 1 : sumI - 8, sumJ) + dfs(m,
                n, k, i,
                j + 1, sumI, (j + 1) % 10 != 0 ? sumJ + 1 : sumJ - 8);
    }

    // 方法二：BFS
    public int movingCount2(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if (i >= m || j >= n || k < si + sj || visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj});
            queue.add(new int[]{i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
        }
        return res;
    }
}

