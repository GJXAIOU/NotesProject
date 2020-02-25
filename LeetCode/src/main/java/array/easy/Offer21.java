package array.easy;


/**
 * @Author GJXAIOU
 * @Date 2020/2/25 15:57
 */
public class Offer21 {
    // 方法一：首位双指针，元素相对位置会变
    public int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] % 2 == 0 && nums[right] % 2 != 0) {
                swap(nums, left, right);
            } else if (nums[left] % 2 == 0) {
                right--;
            } else if (nums[left] % 2 != 0) {
                left++;
            }
        }
        return nums;
    }

    public void swap(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }

    // 方法二：快慢指针
    public int[] exchange2(int[] nums) {
        int low = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] % 2 != 0) {
                swap(nums, low, fast);
                low++;
            }
            fast++;
        }
        return nums;
    }
}
