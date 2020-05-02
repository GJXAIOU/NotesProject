package array.easy;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * @author GJXAIOU
 * @create 2019-08-27-20:42
 */

public class Leetcode167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] ansArray = new int[2];
        // 当 left < right 判断值的和是否为 target
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // 因为开始坐标从 1 开始
                ansArray[0] = left + 1;
                ansArray[1] = right + 1;
                return ansArray;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return ansArray;
    }
}
