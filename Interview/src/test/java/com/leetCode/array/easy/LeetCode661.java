package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/29 17:29
 */
public class LeetCode661 {
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0 || M == null) {
            return M;
        }

        int Row = M.length;
        int Column = M[0].length;
        int[][] ans = new int[Row][Column];

        for (int r = 0; r < Row; ++r) {
            for (int c = 0; c < Column; ++c) {
                int count = 0;
                // 对于数组中每一个元素都访问其周边所有元素
                for (int nr = r - 1; nr <= r + 1; ++nr) {
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < Row && 0 <= nc && nc < Column) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                }
                ans[r][c] /= count;
            }
        }
        return ans;
    }
}