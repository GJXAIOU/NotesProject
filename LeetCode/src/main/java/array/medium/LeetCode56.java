package array.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author GJXAIOU
 * @create 2020/05/03 14:47
 */
public class LeetCode56 {
    // 方法一：排序
    public int[][] merge(int[][] intervals) {
        // 先按照区间开始位置进行排序
        Arrays.sort(intervals, new MyCompare());

        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            // 如果结果数组为空，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，则不合并，即直接将该数组加入结果数组中
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                // 否则则将当前区间合并到结果数组的最后区间中
                res[index][1] = Math.max(interval[1], res[index][1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }

    public class MyCompare implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}
