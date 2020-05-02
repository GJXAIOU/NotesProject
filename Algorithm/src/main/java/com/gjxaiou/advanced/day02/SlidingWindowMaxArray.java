package com.gjxaiou.advanced.day02;

import java.util.LinkedList;

/**
 * @Author GJXAIOU
 * @Date 2020/1/1 20:29
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // LinkedList 就是一个标准的双向链表
        LinkedList<Integer> maxList = new LinkedList<Integer>();
        // 生成的结果数组
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 更新双端队列，如果双端队列不为空，并且尾结点(存的是下标)对应数组中的值是否小于等于当前值
            while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[i]) {
                maxList.pollLast();
            }
            // 上面一直弹出，直到不符合然后加上当前值。
            maxList.addLast(i);
            // 上面加法是通用的，但是减法是针对该题定制的
            // 当过期的时候（当窗口形成之后再扩充才算过期）即窗口长度 > w，窗口形成过程中不会过期, i - w表示过期的下标
            if (maxList.peekFirst() == i - w) {
                maxList.pollFirst();
            }
            // 判断下标过期
            if (i >= w - 1) {
                // 当窗口已经形成了，记录每一步的res
                res[index++] = arr[maxList.peekFirst()];
            }
        }
        return res;
    }

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
