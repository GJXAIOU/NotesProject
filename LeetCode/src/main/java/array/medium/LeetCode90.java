package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/05 20:48
 */
public class LeetCode90 {
    // 回溯法
    List<List<Integer>> resList = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return resList;
        }
        Arrays.sort(nums);
        List<Integer> path = new LinkedList<>();
        backtrack(nums, path, 0);
        return resList;
    }

    public void backtrack(int[] nums, List<Integer> path, int start) {
        resList.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            //剪枝去重
            if (i > start && (nums[i] == nums[i - 1])) {
                continue;
            }
            // 做出选择
            path.add(nums[i]);
            // 递归进入下一层，注意start+1，标识下一个选择列表的开始位置，最重要的一步
            backtrack(nums, path, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
