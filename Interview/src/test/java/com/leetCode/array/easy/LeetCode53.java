package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/2 15:22
 */
public class LeetCode53 {
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    // 分别求 leftSum/crossSum/rightSum 的值
    public int helper(int[] nums, int left, int right) {
        // 只剩下一个数
        if (left == right) {
            return nums[left];
        }

        int mid = (left + right) >>> 1;

        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }


    public int crossSum(int[] nums, int left, int right, int mid) {
        if (left == right) {
            return nums[left];
        }

        // 从中间值（包括中间值）不断往左加，取最大值
        int leftSubSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = mid; i > left - 1; --i) {
            currSum += nums[i];
            leftSubSum = Math.max(leftSubSum, currSum);
        }

        // 从中间值（不包括中间值）不断往右加，取最大值
        int rightSubSum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = mid + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubSum = Math.max(rightSubSum, currSum);
        }

        return leftSubSum + rightSubSum;
    }

    // 方法三：动态规划
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int ans = nums[0];
        for (int i = 0; i < n; ++i) {
            // 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
            if (sum > 0) {
                sum += nums[i];
                // 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
            } else {
                sum = nums[i];
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }
}
