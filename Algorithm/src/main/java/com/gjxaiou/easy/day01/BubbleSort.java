package com.gjxaiou.easy.day01;

import java.util.Arrays;

/**
 * @author GJXAIOU
 * @create 2019-10-04-20:00
 */

public class BubbleSort {
    ////////////////// 冒泡排序 /////////////////////////
    public static void bubbleSort(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length < 2) {
            return;
        }
        // end 刚开始在 length-1,但是得大于零，每排完一圈减一
        for (int end = sourceArray.length - 1; end > 0; end--) {
            for (int start = 0; start < end; start++) {
                if (sourceArray[start] > sourceArray[start + 1]) {
                    swap(sourceArray, start, start + 1);
                }
            }
        }
    }

    public static void swap(int[] sourceArray, int left, int right) {
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
        sourceArray[right] = sourceArray[left] ^ sourceArray[right];
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
    }

    ////////////////////// 使用对数器 ////////////////////////
    // 1.想要验证的方法，见上
    // 2.准备一个绝对正确的方法：这里使用系统自带的排序方法
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // 3.实现一个随机样本产生器：这里随机生成一个任意长度，值为任意的数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() 表示范围为： double [0,1)
        // (int)((maxSize + 1) * Math.random())：int [0,size]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 因为是原地排序，会改变原数组，所以复制一份两个算法使用
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

    // 4.实现连个方法对比的方法
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // 可有可无
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i : arr) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    // 最后：进行大样本测试
    public static void main(String[] args) {
        int testTime = 500000;
        // 长度为【0-100】
        int maxSize = 100;
        // 值为 【-100-100】之间
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Bad!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
//  自己默写
//class MyBubble {
//    public static void bubbleSort(int[] inputArray) {
//        if (inputArray == null || inputArray.length <= 2) {
//            return;
//        }
//        for (int end = inputArray.length - 1; end > 0; end--) {
//            for (int start = 0; start < end; start++) {
//                if (inputArray[start] > inputArray[start + 1]) {
//                    swap(inputArray, start, start + 1);
//                }
//            }
//        }
//    }
//
//    public static void swap(int[] inputArray, int left, int right) {
//        if (left == right) {
//            return;
//        }
//        inputArray[left] = inputArray[left] ^ inputArray[right];
//        inputArray[right] = inputArray[left] ^ inputArray[right];
//        inputArray[left] = inputArray[left] ^ inputArray[right];
//    }
//
//    public static void main(String[] args) {
//        int[] inputArray = new int[]{2,3,1,34,4,2,4,243};
//        bubbleSort(inputArray);
//       Arrays.stream(inputArray).forEach(System.out::println);
//    }
//}
