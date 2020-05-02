package array.medium;

import com.sun.istack.internal.localization.NullLocalizable;

/**
 * @Author GJXAIOU
 * @Date 2020/2/10 21:01
 */
public class LeetCode192 {
    // 方法一：线性查找
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        // 如果一直没有，则表示是递增数列，就是最后一个
        return nums.length - 1;
    }

    // 方案：二分查找
    public int findPeakElement2(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        int res = find(nums, 0, nums.length - 1);
        return res;

    }

    public int find(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) >>> 1;
        if (nums[mid] > nums[mid + 1]) {
            return find(nums, left, mid);
        } else {
            return find(nums, mid + 1, right);
        }
    }

    // 方案二：迭代版本
    public int findPeakElement3(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
