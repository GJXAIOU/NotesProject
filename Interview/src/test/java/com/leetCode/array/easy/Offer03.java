package array.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 9:38
 */
public class Offer03 {
    // 方法一：使用 HashMap
    public int findRepeatNumber(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return -1;
        }

        HashMap<Integer, Integer> resMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (resMap.containsKey(nums[i])) {
                int oldValue = resMap.get(nums[i]);
                int newValue = oldValue + 1;
                if (newValue >= 2) {
                    return nums[i];
                } else {
                    resMap.replace(nums[i], oldValue, newValue);
                }
            } else {
                resMap.put(nums[i], 1);
            }
        }
        return -1;
    }

    // 方法二：排序然后双指针
    public int findRepeatNumber2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return -1;
        }
        Arrays.sort(nums);
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                return nums[fast];
            } else {
                fast++;
                slow++;
            }
        }
        return -1;
    }


    // 方法三：桶排序，将 nums[i] 元素放置在 i 位置上
    public int findRepeatNumber3(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return -1;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
}
