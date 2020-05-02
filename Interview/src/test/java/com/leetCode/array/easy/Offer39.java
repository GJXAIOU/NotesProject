package array.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 22:31
 */
public class Offer39 {
    // 方法一：Hash 计数
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int oldValue = map.get(nums[i]);
                int newValue = oldValue + 1;
                if (newValue > nums.length >>> 1) {
                    return nums[i];
                } else {
                    map.replace(nums[i], oldValue, newValue);
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }

    // 方法二：因为是众数，数量超过数组长度一半，则数组中间值一定为所求值
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    // 方法三：位运算
    public int majorityElement3(int[] nums) {
        int[] bit = new int[32];
        int n = nums.length;
        for (int a : nums) {
            for (int i = 0; i < 32; i++) {
                //无符号右移; 负数的无符号右移移动的是补码还是原码?
                if (((a >>> i) & 1) == 1) {
                    bit[i]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (bit[i] > n / 2) {
                res = res | (1 << i);
            }
        }
        return res;
    }

}
