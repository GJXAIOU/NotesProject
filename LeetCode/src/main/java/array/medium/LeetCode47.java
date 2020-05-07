package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/06 13:34
 */
public class LeetCode47 {
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return resList;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, path, used);
        return resList;
    }

    public void backtrack(int[] nums, List<Integer> path, boolean[] used) {
        // 结束条件
        if (path.size() == nums.length) {
            resList.add(new ArrayList<>(path));
            return;
        }
        // 选择列表
        for (int i = 0; i < nums.length; i++) {
            //从给定的数中除去，用过的数，就是当前的选择列表
            if (!used[i]) {
                // 剪枝
                if ((i > 0) && (nums[i - 1] == nums[i]) && !used[i - 1]) {
                    continue;
                }
                // 做出选择
                path.add(nums[i]);
                used[i] = true;
                // 进入下一层
                backtrack(nums, path, used);
                // 撤销选择
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
