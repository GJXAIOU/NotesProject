package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/12 14:51
 */
public class Offer63 {
    // 自己的动态规划
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices == null) {
            return 0;
        }
        // dp[i] 表示前 i 日的最大利润
        int[] dp = new int[prices.length + 1];
        // 便于理解，这里的 dp[0] 不使用，dp[1] 就是表示前 1 天可以获取的利润，就是 0
        dp[1] = 0;
        // 保存前 i 天的最低成本，默认成本就是第一天成本
        int lowestCost = prices[0];
        for (int i = 2; i <= prices.length; i++) {
            lowestCost = Math.min(lowestCost, prices[i - 1]);
            dp[i] = Math.max(dp[i - 1], prices[i - 1] - lowestCost);
        }
        return dp[prices.length];
    }

    // 题解的 DP
    public int maxProfit2(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

}
