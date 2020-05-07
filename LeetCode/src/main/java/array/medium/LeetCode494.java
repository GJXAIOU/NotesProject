package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/05/07 17:20
 */
public class LeetCode494 {
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            maxSum += nums[i];
        }
        if (S > maxSum) {
            return 0;
        }
        calculate(nums, 0, 0, S);
        return count;
    }

    public void calculate(int[] nums, int start, int sum, int S) {
        if (start == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, start + 1, sum + nums[start], S);
            calculate(nums, start + 1, sum - nums[start], S);
        }
    }

    // 方法二：DP

    public int findTargetSumWays2(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    // 方法三：DP 优化
    public int findTargetSumWays3(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}
