package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 18:06
 */
public class LeetCode509 {
    // 方法一：递归
    public int fib(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fib(N - 1) + fib(N - 2);
        }
    }

    // 方法二：动态规划
    public int fib2(int N) {
        int[] dp = new int[N + 2];
        dp[0] = 0;
        dp[1] = 1;
        if (N < 2) {
            return dp[N];
        }
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N - 1] + dp[N - 2];
    }

    // 方法三：动态规划优化
    public int fib3(int N) {
        int curr = 0, next = 1;
        while (N-- > 0) {
            next = next + curr;
            curr = next - curr;
        }
        return curr;
    }
}
