package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/19 16:34
 */
public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 首先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // K 从 0 到 nums.length - 2
        for (int k = 0; k < nums.length - 2; k++) {
            // 如果 nums[k] > 0，则肯定不符合
            if (nums[k] > 0) {
                break;
            }

            // 如果两个相等，则该可能性已经算过
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }

            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    // sum < 0 则 i 值一直往右（并且排除所有想等值的情况）
                    while (i < j && nums[i] == nums[++i]) {
                        ;
                    }
                } else if (sum > 0) {
                    // sum > 0 则 j 值往左
                    while (i < j && nums[j] == nums[--j]) {
                        ;
                    }
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]) {
                        ;
                    }
                    while (i < j && nums[j] == nums[--j]) {
                        ;
                    }
                }
            }
        }
        return res;
    }
}
