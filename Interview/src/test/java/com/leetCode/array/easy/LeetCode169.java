package array.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/2 13:06
 */
public class LeetCode169 {
    // 方法一：使用HashMap
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果有该元素，则数目加一
            if (map.containsKey(nums[i])) {
                int oldValue = map.get(nums[i]);
                int newValue = oldValue + 1;
                // 如果数目到一半了，返回
                if (newValue > nums.length >> 1) {
                    return nums[i];
                } else {
                    map.replace(nums[i], oldValue, newValue);
                }
                // 如果没有元素，加入该元素，出现次数设置为 1
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }

    // 方法二：投票
    public int majorityElement2(int[] nums) {
        int count = 0;
        // 候选数
        Integer candidate = null;

        for (int i = 0; i < nums.length; i++) {
            // 如果计数为 0，当前数作为下一个候选数
            if (count == 0) {
                candidate = nums[i];
            }
            count += (nums[i] == candidate) ? +1 : -1;
        }
        return candidate;
    }

}
