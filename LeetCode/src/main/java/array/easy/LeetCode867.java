package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 22:42
 */
public class LeetCode867 {
    public int[][] transpose(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        }
        return ans;
    }
}
