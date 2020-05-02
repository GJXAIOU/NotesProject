package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/8 18:16
 */
public class LeetCode238 {

    public int[] productExceptSelf(int[] nums) {

        int length = nums.length;
        // left[i] 表示位置 i 左边所有元素乘积
        int[] left = new int[length];
        // right[i] 表示位置 i 右边所有元素乘积
        int[] right = new int[length];

        int[] answer = new int[length];

        // 分别填充 left 和 right 数组
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        for (int i = 0; i < length; i++) {
            answer[i] = left[i] * right[i];
        }

        return answer;
    }

    // 方法二：省略构造 right 数组
    public int[] productExceptSelf2(int[] nums) {

        int length = nums.length;
        int[] ans = new int[length];
        // ans[i] 为 i 位置左边所有元素的乘积
        ans[0] = 1;
        for (int i = 1; i < length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            ans[i] = ans[i] * R;
            R *= nums[i];
        }

        return ans;
    }

}
