package array.easy;

import java.util.Arrays;

/**
 * @author GJXAIOU
 * @create 2020/05/22 15:13
 */
public class LeetCode1051 {
    public int heightChecker(int[] heights) {
        int[] copyArray = Arrays.copyOf(heights, heights.length);

        Arrays.sort(heights);
        int num = 0;

        for (int i = 0; i < heights.length; i++) {
            if (copyArray[i] != heights[i]) {
                num++;
            }
        }
        return num;
    }
}
