package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 10:31
 */
public class Offer57 {
    // 方法二：双指针
    public int[] twoSum(int[] nums, int target) {
        // 如果 target < nums[0] 即数组最小值，则不可能
        if (target < nums[0]) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return nums;
    }

}
