package array.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 17:46
 */
public class Offer40 {
    // 方法一：排序
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k > arr.length) {
            return new int[0];
        }
        Arrays.sort(arr);
        int[] resArray = new int[k];
        for (int i = 0; i < k; i++) {
            resArray[i] = arr[i];
        }
        return resArray;

    }
}
