package array.easy;

import java.util.Random;

/**
 * @Author GJXAIOU
 * @Date 2020/1/19 16:30
 */
public class LeetCode27 {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 慢指针在数组第一位，快指针在数组最后一位
        int slow = 0;
        int fast = nums.length - 1;
        while (slow != fast) {
            if (nums[slow] == val) {
                nums[slow] = nums[fast];
                fast--;
            } else {
                slow++;
            }
        }
        return slow + 1;
    }


    // --------对数器------
    // 1.绝对正确的方法
    public static int absoluteRemoveElement(int[] nums, int val) {
        // 准备一个空数组，放置 nums 中不等于 val 的值
        int[] resNum = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                resNum[j] = nums[i];
                j++;
            }
        }
        return j;
    }


    // 2.随机数产生器
    public static int[] generateRamdom(int length) {
        int[] nums = new int[length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new Random().nextInt();
        }
        return nums;
    }

    // 3.相等方法省略，直接比较两个数字是否相等即可

    public static void main(String[] args) {
        int[] ints = generateRamdom(15);
        LeetCode27 leetCode27 = new LeetCode27();
        int i = leetCode27.removeElement(ints, 23);
        int i1 = absoluteRemoveElement(ints, 23);
        System.out.println("i: " + i + ", i1: " + i1);
        System.out.println(i == i1);
    }
}
