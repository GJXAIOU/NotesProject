package array.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 15:38
 */
public class LeetCode350 {
    // 方法一：哈希映射
    public int[] intersect(int[] nums1, int[] nums2) {
        // 如果 nums1 中元素个数大于 nums2 中元素个数，则交换两数组
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // 将 nums1 中所有元素放入 HashMap 中
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // count 为当前交集元素个数
        int count = 0;
        for (int num : nums2) {
            int cnt = map.getOrDefault(num, 0);
            // cnt 为当前元素 num 是否存在，如果 cnt > 0 则表示存在；
            if (cnt > 0) {
                // 将元素拷贝到 nums1，同时在 map 中减少该元素的个数
                nums1[count++] = num;
                map.put(num, cnt - 1);
            }
        }
        // nums1 中前 count 即为交集元素
        return Arrays.copyOfRange(nums1, 0, count);
    }


// 方法二：

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        // 对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int slow = 0;
        int fast = 0;
        int count = 0;
        while (slow < nums1.length && fast < nums2.length) {
            if (nums1[slow] < nums2[fast]) {
                ++slow;
            } else if (nums1[slow] > nums2[fast]) {
                ++fast;
            } else {
                int j = 0;
                nums1[count++] = nums1[slow++];
                ++fast;
            }
        }
        return Arrays.copyOfRange(nums1, 0, count);
    }

}
