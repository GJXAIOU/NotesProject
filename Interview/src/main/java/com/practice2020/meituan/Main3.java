package com.practice2020.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/16 20:12
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] input = new int[n];
        int i = 0;
        while (i < input.length){
            input[i] = scanner.nextInt();
            i++;
        }
        // 先排序
       Arrays.sort(input);
        //sortQuick(input, 0, input.length - 1);
        // 获取左边坐标
        int left = k / input.length;
        // 获取右边坐标
        int right = k - input.length * left;
        if (k % input.length == 0) {
            left -= 1;
        }
        if (right == 0) {
            right = input.length - 1;
        }else {
            right -=1;
        }
        System.out.println("(" + input[left] + "," + input[right] + ")");
    }

    // 快排
    public static void sortQuick(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            sortQuick(array, left, pivot - 1);
            sortQuick(array, pivot + 1, right);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = right;
        int temp = 0;
        while (l < r) {
            while (l < r && array[l] <= array[pivot]) {
                l++;
            }
            while (l < r && array[r] >= array[pivot]) {
                r--;
            }
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
        }
        temp = array[l];
        array[l] = array[pivot];
        array[pivot] = temp;
        return l;
    }
}
