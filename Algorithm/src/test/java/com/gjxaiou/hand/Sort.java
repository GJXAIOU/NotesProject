package com.gjxaiou.hand;

import java.time.temporal.ValueRange;

/**
 * @author GJXAIOU
 * @create 2020/04/25 17:15
 */
public class Sort {
    public void bubble(int[] value) {
        if (value == null || value.length == 0) {
            return;
        }
        for (int end = value.length - 1; end > 0; end--) {
            for (int start = 0; start < end; start++) {
                if (value[start] > value[start + 1]) {
                    swap(value, start, start + 1);
                }
            }
        }
    }

    public void swap(int[] value, int start, int end) {
        value[start] = value[start] ^ value[end];
        value[end] = value[start] ^ value[end];
        value[start] = value[start] ^ value[end];
    }

    public void choose(int[] value) {
        if (value == null || value.length == 0) {
            return;
        }
        int minIndex = 0;
        for (int i = 0; i < value.length - 1; i++) {
            for (int i1 = i + 1; i1 < value.length; i1++) {
                minIndex = value[minIndex] < value[i1] ? minIndex : i1;
            }
            swap(value, i, minIndex);
        }
    }
}
