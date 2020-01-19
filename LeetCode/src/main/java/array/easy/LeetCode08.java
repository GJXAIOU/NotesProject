package array.easy;

import java.util.HashMap;

/**
 * 删除数组中重复项
 *
 * @Author GJXAIOU
 * @Date 2020/1/18 21:49
 */
public class LeetCode08 {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        while (fast != nums.length - 2) {
            if (nums[fast] == nums[slow]) {
                nums[fast] = nums[fast + 1];
                fast++;
            } else {
                slow++;
                fast++;
            }
        }
        return slow;
    }


    //----------对数器---------
    // 1.绝对正确的方法
    // 使用 HashMap，不能做到原地修改，空间复杂度为 O（N）
    public int absoluteRemoveDuplicates(int[] nums) {
        HashMap<Object, Object> k = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            k.put(nums[i], i);
        }
        return k.size();
    }

    // 比较
    public static boolean isEquals(int x, int y) {
        if (x == y) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 9, 10};
        LeetCode08 leetCode08 = new LeetCode08();
        int res1 = leetCode08.removeDuplicates(nums);
        int res2 = leetCode08.absoluteRemoveDuplicates(nums);
        System.out.println(res1);
        System.out.println(res2);
        boolean equals = isEquals(res1, res2);
        System.out.println(equals);
    }
}
