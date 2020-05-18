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

        int row = M.length;
        int column = M[0].length;
        int[][] ans = new int[row][column];

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                // count 为该元素周围元素个数
                int count = 0;
                // 对于数组中每一个元素都访问其周边所有元素
                for (int nr = i - 1; nr <= i + 1; ++nr) {
                    for (int nc = j - 1; nc <= j + 1; ++nc) {
                        if (0 <= nr && nr < row && 0 <= nc && nc < column) {
                            ans[i][j] += M[nr][nc];
                            count++;
                        }
                    }
                }
                ans[i][j] /= count;
            }
        }
        return ans;
    }
}