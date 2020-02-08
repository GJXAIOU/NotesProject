package array.medium;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/2/8 21:06
 */
public class LeetCode287 {

    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            // 首先选取中点
            int mid = (left + right) >>> 1;
            // 统计原数组中严格小于等于该选取值的元素个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count += 1;
                }
            }

            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
            // 此时重复元素一定出现在 [1, 4] 区间里
            if (count > mid) {
                // 重复元素位于区间 [left, mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面
                // [mid + 1, right]
                left = mid + 1;
            }
        }
        return left;

    }
}
