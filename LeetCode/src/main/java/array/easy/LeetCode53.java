package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/2 15:22
 */
public class LeetCode53 {

                public int crossSum(int[] nums, int left, int right, int p) {
                        if (left == right) return nums[left];

                        int leftSubsum = Integer.MIN_VALUE;
                        int currSum = 0;
                        for(int i = p; i > left - 1; --i) {
                                currSum += nums[i];
                                leftSubsum = Math.max(leftSubsum, currSum);
                        }

                        int rightSubsum = Integer.MIN_VALUE;
                        currSum = 0;
                        for(int i = p + 1; i < right + 1; ++i) {
                                currSum += nums[i];
                                rightSubsum = Math.max(rightSubsum, currSum);
                        }

                        return leftSubsum + rightSubsum;
                }

                public int helper(int[] nums, int left, int right) {
                        if (left == right) return nums[left];

                        int p = (left + right) / 2;

                        int leftSum = helper(nums, left, p);
                        int rightSum = helper(nums, p + 1, right);
                        int crossSum = crossSum(nums, left, right, p);

                        return Math.max(Math.max(leftSum, rightSum), crossSum);
                }

                public int maxSubArray(int[] nums) {
                        return helper(nums, 0, nums.length - 1);
                }

}
