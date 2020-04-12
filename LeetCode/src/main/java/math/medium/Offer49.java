package math.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/12 14:43
 */
public class Offer49 {

    public int nthUglyNumber(int n) {
        // 初始索引均为 0
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            // dp[i] 为其中最小值
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

}
