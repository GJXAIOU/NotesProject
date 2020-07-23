package array.easy;

import java.util.Arrays;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:30
 */
public class LeetCode977 {
    // 方法一：排序
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i) {
            ans[i] = A[i] * A[i];
        }

        Arrays.sort(ans);
        return ans;
    }

    // 方法二：双指针
    public int[] sortedSquares2(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0) {
            j++;
        }
        int i = j - 1;

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }
}
