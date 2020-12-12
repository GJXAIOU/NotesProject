package com.gjxaiou.advanced.day06;

/**
 * 全正数数组中累加和为 aim 的最长子数组长度
 */
public class LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        // 初始状态，窗口左边界以及右边界都在 0 位置
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            // 如果和正好为 aim，比较当前窗口长度和之前窗口长度，取较大的一个
            if (sum == aim) {
                len = Math.max(len, right - left + 1);
                // 左边界向右移动（left++ 并且将 left 值从 sum 中减去）
                sum -= arr[left++];
                // 如果和小于值，右边界右移：右移就是 right++，并且将 right 值加入 sum
            } else if (sum < aim) {
                right++;
                // 防止越界
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
                // 如果和大于值， left 右移
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    //////////////  测试程序 //////////////////
    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));
    }
}
