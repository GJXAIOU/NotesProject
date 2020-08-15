package array.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/7/27 23:12
 */
public class LeetCode985 {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        // 首先求出数组中所有偶数的和
        int sum = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                sum += x;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0], index = queries[i][1];
            if (A[index] % 2 == 0) {
                sum -= A[index];
            }
            A[index] += val;
            if (A[index] % 2 == 0) {
                sum += A[index];
            }
            ans[i] = sum;
        }

        return ans;
    }

}
