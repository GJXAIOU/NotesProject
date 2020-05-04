package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/04 14:50
 */
public class LeetCode35 {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        if (target <= nums[0]) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
            if (target > nums[i - 1] && target < nums[i]) {
                return i;
            }
        }
        // 如果以上都不符合则说明 target 大于最后一个数。
        return nums.length;
    }
}
