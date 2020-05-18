package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 13:45
 */
public class LeetCode665 {
    // 方法一：
    public static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            // 如果后一个小于前一个数
            if (nums[i] < nums[i - 1]) {
                // 已经改变过一次了
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

    // 方法二：
    public boolean checkPossibility2(int[] nums) {
        //遍历一遍数组，如果后一个值小于前一个值，则计数+1
        int length = nums.length;
        int count = 0;
        //如果只有两个元素一定成立
        if (length < 3) {
            return true;
        }


        if (nums[0] > nums[1]) {
            nums[0] = nums[1];
            count++;
        }

        for (int i = 1; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
                if (nums[i + 1] > nums[i - 1]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }
}
