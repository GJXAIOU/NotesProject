package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/13 15:02
 */
public class LeetCode746 {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);

        for (int i = 2; i < cost.length; i++) {
            dp[i] += Math.min(dp[i - 2] + cost[i - 1], dp[i - 1] + cost[i]);
        }
        return dp[cost.length - 1];
    }
}
