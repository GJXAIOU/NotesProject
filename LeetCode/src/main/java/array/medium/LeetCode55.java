package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/17 21:08
 */
public class LeetCode55 {
    // 方法一：回溯
    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        // 从下一个位置开始，到最大位置，不是改为的值
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }

        return false;
    }


    // 贪心问题
    public boolean canJump2(int[] nums) {
        int mostArrive = 0;
        for (int i = 0; i < nums.length; i++) {
            // 保证 i  位置是可达的
            if (i <= mostArrive) {
                mostArrive = Math.max(mostArrive, i + nums[i]);
                if (mostArrive >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
