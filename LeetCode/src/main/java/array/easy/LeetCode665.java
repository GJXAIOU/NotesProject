package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 13:45
 */
public class LeetCode665 {
    public static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (count == 1) {
                    return false;
                }
                if ((i - 2 >= 0) && nums[i] < nums[i - 2]) {
                    nums[i] = nums[i - 1];
                }
                count++;
            }
        }
        return true;
    }
}
