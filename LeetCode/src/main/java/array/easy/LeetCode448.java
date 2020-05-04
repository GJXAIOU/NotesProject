package array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/29 21:44
 */
public class LeetCode448 {
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            ArrayList<Integer> resList = new ArrayList<>();
            if (nums.length == 0 || nums == null) {
                return resList;
            }

            for (int i = 0; i < nums.length; i++) {
                // 因为 num[i] 值会变为负值，所以取绝对值
                int temp = Math.abs(nums[i]);
                // 将该值 - 1 对应坐标位置的值变为负数，如果已经为负数就不动了（可能 2 个重复值又变成正的了）
                nums[temp - 1] *= nums[temp - 1] < 0 ? 1 : -1;
            }

            // i 位置值 > 0，说明没有 i + 1 值让它变为负值，所以就说明缺 i + 1 值
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    resList.add(i + 1);
                }
            }
            return resList;
        }
    }
}
