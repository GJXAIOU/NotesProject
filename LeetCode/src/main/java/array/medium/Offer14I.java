package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/12 10:16
 */
public class Offer14I {
    // 方法一：数学方法：尽量切分为长度为 3 的等长
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int num = n / 3;
        int remain = n % 3;
        if (remain == 0) {
            return (int) Math.pow(3, num);
        } else if (remain == 1) {
            return (int) Math.pow(3, num - 1) * 2 * 2;
        } else if (remain == 2) {
            return (int) Math.pow(3, num) * 2;
        }
        return 0;
    }

    // 方法二：暴力递归加上记忆化搜索
    public int cuttingRope2(int n) {
        if (n == 2) {
            return 1;
        }
        int ans[] = new int[n + 1];
        if (ans[n] != 0) {
            return ans[n];
        }

        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, Math.max(i * cuttingRope2(n - i), i * (n - i)));
            ans[n] = res;
        }
        return ans[n];
    }

    // 方法三：动态规划
    public int cuttingRope3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        int res = 0;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, j * dp[i - j]));
            }
        }
        return dp[n];
    }

}
