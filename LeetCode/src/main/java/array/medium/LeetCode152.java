package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/05/03 14:17
 */
public class LeetCode152 {
    // DP 方式一
    public int maxProduct(int[] nums) {
        // 分别维护一个当前最大值和最小值，初始值设置为 1；
        int maxValue = 1;
        int minValue = 1;
        int maxRes = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = minValue;
                minValue = maxValue;
                maxValue = temp;
            }
            maxValue = Math.max(maxValue * nums[i], nums[i]);
            minValue = Math.min(minValue * nums[i], nums[i]);

            maxRes = Math.max(maxRes, maxValue);
        }
        return maxRes;
    }

    // DP 方式二：
    public int maxProduct2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int maxRes = nums[0];
        int maxValue = nums[0];
        int minValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = maxValue;
            maxValue = Math.max(Math.max(maxValue * nums[i], nums[i]), minValue * nums[i]);
            minValue = Math.max(Math.min(temp * nums[i], nums[i]), minValue * nums[i]);
            maxRes = Math.max(maxValue, maxRes);
        }
        return maxRes;
    }
}
