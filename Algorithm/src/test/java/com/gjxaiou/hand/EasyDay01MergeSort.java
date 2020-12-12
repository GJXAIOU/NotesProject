package com.gjxaiou.hand;

/**
 * @Author GJXAIOU
 * @Date 2020/2/14 21:51
 */

// 再亲自手撸一遍,2020.2.13
class EasyDay01MergeSort {
    public void mergeSort(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length == 0) {
            return;
        }
        mergeSort(sourceArray, 0, sourceArray.length - 1);

    }

    public void mergeSort(int[] sourceArray, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort(sourceArray, 0, mid);
        mergeSort(sourceArray, mid + 1, right);
        merge(sourceArray, left, mid, right);
    }

    public void merge(int[] sourceArray, int left, int mid, int right) {
        int[] help = new int[sourceArray.length];
        int startLeft = left;
        int startRight = mid + 1;
        int i = 0;
        while (startLeft <= mid && startRight <= right) {
            help[i++] = sourceArray[startLeft] < sourceArray[startRight] ?
                    sourceArray[startLeft] : sourceArray[startRight];
        }

        while (startLeft <= mid) {
            help[i++] = sourceArray[startLeft++];
        }
        while (startRight <= right) {
            help[i++] = sourceArray[startRight++];
        }
        for (int i1 = 0; i1 < help.length; i1++) {
            sourceArray[left + i1] = help[i1];
        }
    }

}
