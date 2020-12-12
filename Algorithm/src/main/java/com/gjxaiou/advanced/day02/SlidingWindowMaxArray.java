package com.gjxaiou.advanced.day02;

import java.util.LinkedList;

/**
 * @Author GJXAIOU
 * @Date 2020/1/1 20:29
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int windowLength) {
        if (arr == null || windowLength < 1 || arr.length < windowLength) {
            return null;
        }

        LinkedList<Integer> maxList = new LinkedList<Integer>();
        int[] resList = new int[arr.length - windowLength + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 更新双端队列，如果双端队列不为空，并且尾结点(存的是下标)对应数组中的值是否小于等于当前值
            while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[i]) {
                maxList.pollLast();
            }

            // 上面一直弹出，直到不符合然后加上当前值。
            maxList.addLast(i);

            // 当过期的时候（当窗口形成之后再扩充才算过期）即窗口长度 > windowLength，窗口形成过程中不会过期, i - windowLength 表示过期的下标
            if (maxList.peekFirst() == i - windowLength) {
                maxList.pollFirst();
            }

            // 判断下标过期
            if (i >= windowLength - 1) {
                // 当窗口已经形成了，记录每一步的 res
                resList[index++] = arr[maxList.peekFirst()];
            }
        }
        return resList;
    }

    /**
     * 测试程序
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        printArray(getMaxWindow(arr, 3));
    }
}


