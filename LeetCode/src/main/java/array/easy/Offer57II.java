package array.easy;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 14:19
 */
public class Offer57II {

    // 方法：滑动窗口
    public int[][] findContinuousSequence(int target) {
        if (target < 3) {
            return new int[1][0];
        }
        ArrayList<int[]> resList = new ArrayList<int[]>();
        int left = 1;
        int right = 2;
        // 初始和为 1
        int curSum = left;


        // 因为如果 target 的 mid 位置值 + （mid + 1） 位置值肯定大于 target，所以到 mid 位置即可
        int mid = (left + target) >>> 1;
        // 区间为左闭右开
        while (left < mid) {
            // 和不断往右相加，如果和等于 target 则符合一种情况
            curSum += right;
            right++;
            if (curSum == target) {
                resList.add(count(left, right));
            }
            while (curSum > target) {
                curSum -= left;
                left++;
                if (curSum == target) {
                    resList.add(count(left, right));
                }
            }

        }
        return resList.toArray(new int[0][]);
    }

    public int[] count(int left, int right) {
        int i = 0;
        int length = right - left;
        int[] temp = new int[length];
        while (i < length) {
            temp[i] = i + left;
            i++;
        }
        return temp;
    }

}