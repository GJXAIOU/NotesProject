package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/9 9:31
 */
public class LeetCode11 {
    // 方法一：暴力法
    public int maxArea(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length - 1; i++) {
            // j 从 i + 1 位置即可
            for (int j = i + 1; j < height.length; j++) {
                ans = Math.max(ans, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return ans;
    }

    // 方法二：双指针法
    public int maxArea2(int[] height) {
        // 最初位置分别位于开头和结尾
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while (left < right) {
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
