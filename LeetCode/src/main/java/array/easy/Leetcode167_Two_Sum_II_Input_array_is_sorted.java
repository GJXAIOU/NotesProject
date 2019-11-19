package array.easy;

/** 167. 两数之和 II - 输入有序数组
 * @author GJXAIOU
 * @create 2019-08-27-20:42
 *
 * 题目描述
 *给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 */

public class Leetcode167_Two_Sum_II_Input_array_is_sorted {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] ansArray = new int[2];
        // 当 left < right 判断值的和是否为 target
        while(left < right){
            int sum = numbers [left] + numbers [right];
            if (sum == target){
                ansArray[0] = left + 1;
                ansArray[1] = right + 1;
                return ansArray;
            }else if (sum > target) {
                right--;
            }else {
                left++;
            }
        }
    return ansArray;
    }
}
/**
 * 执行用时 :2 ms, 在所有 Java 提交中击败了82.32%的用户
 * 内存消耗 :38.9 MB, 在所有 Java 提交中击败了20.07%的用户
 */
