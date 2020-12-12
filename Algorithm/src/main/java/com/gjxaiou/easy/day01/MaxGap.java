package com.gjxaiou.easy.day01;

import java.util.Arrays;

/**
 * 排序后相邻两元素之间的最大插值
 */
public class MaxGap {
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        // 找到数组中的最大值和最小值
        for (int i = 0; i < len; i++) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (minValue == maxValue) {
            return 0;
        }

        // 下面三个数组是描述 len + 1 个桶中每个桶的三个必备信息
        boolean[] hasNum = new boolean[len + 1];
        int[] maxArray = new int[len + 1];
        int[] minArray = new int[len + 1];
        int bucketIndex = 0;
        for (int i = 0; i < len; i++) {
            // 确定该数去第几号桶
            bucketIndex = bucket(nums[i], len, minValue, maxValue);
            // 该桶中的三个信息进行更新
            minArray[bucketIndex] = hasNum[bucketIndex] ? Math.min(minArray[bucketIndex],
                    nums[i]) : nums[i];
            maxArray[bucketIndex] = hasNum[bucketIndex] ? Math.max(maxArray[bucketIndex],
                    nums[i]) : nums[i];
            hasNum[bucketIndex] = true;
        }
        // 找到每一个非空桶和离他最近的非空桶的插值：用当前min - 前一个max；
        int res = 0;
        int lastMax = maxArray[0];
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, minArray[i] - lastMax);
                lastMax = maxArray[i];
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }



    ///////////// 对数器 //////////////////
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Bad");
    }
}
