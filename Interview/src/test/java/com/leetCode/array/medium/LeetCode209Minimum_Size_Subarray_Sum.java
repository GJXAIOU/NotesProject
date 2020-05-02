package array.medium;

/**
 * @author GJXAIOU
 * @create 2019-08-28-19:20
 */
public class LeetCode209Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int s, int[] nums) {
        // 保证初始状态下滑动窗口长度 [left...right] 为空
        int left = 0;
        int right = -1;
        int sum = 0;
        // 设置默认最小滑动窗口为 nums.length + 1
        int defaultLength = nums.length + 1;
        while (left < nums.length){
            if ((right + 1) < nums.length && sum < s){
                right++;
                sum += nums [right];
            }else {
                sum -= nums [left];
                left++;
            }
            // 当满足条件的时候返回
            if (sum >= s){
                defaultLength = defaultLength < right - left + 1 ? defaultLength :
                        right - left + 1;
            }
        }
        if (defaultLength == nums.length + 1){
            return 0;
        }
        return defaultLength;
    }
}
