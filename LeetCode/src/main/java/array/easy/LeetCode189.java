package array.easy;

import jdk.nashorn.internal.objects.annotations.Where;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author GJXAIOU
 * @Date 2020/1/21 16:24
 */
public class LeetCode189 {
    // 方法二：额外数组
    public void rotate2(int[] nums, int k) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
    }


    public void rotate(int[] nums, int k) {
        // 因为如果 k 比数组长度长的话，倍数就是整个数组翻转的次数，余数就是最终仍要平移的次数；
        k %= nums.length;
        // 首先将整个数组翻转
        reverse(nums, 0, nums.length - 1);
        // 将前 K 个翻转
        reverse(nums, 0, k - 1);
        // 将后面的反转
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // reverse2 可以替换如下
    public void reverse2(int[] nums, int begin, int end){
        while (begin < end){
            nums[begin] = nums[begin] ^ nums[end];
            nums[end] = nums[begin] ^ nums[end];
            nums[begin] = nums[begin] ^ nums[end];
            begin++;
            end--;
        }
    }
}
