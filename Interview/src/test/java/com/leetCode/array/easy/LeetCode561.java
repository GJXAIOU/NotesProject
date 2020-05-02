package array.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/1/29 15:06
 */
public class LeetCode561 {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 首先对数组进行排序
        Arrays.sort(nums);

        // 将所有奇数位数值相加即可
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}
