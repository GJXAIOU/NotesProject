package array.easy;


public class Offer10I {

    // 方案一：记忆化搜索
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        // 使用一个数组来保存已经计算完成的结果
        int[] ans = new int[n + 1];
        ans[0] = 0;
        ans[1] = 1;
        if (ans[n] != 0) {
            return ans[n];
        } else {
            ans[n] = fib(n - 2) + fib(n - 1);
        }
        return ans[n];
    }

    // 方案二：动态规划
    public int fib2(int n) {
        if(n <= 1){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

}
