package array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 10:32
 */
public class LeetCode78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++) {
                // i 向右移动 j 位，然后看最后一位是否为 1
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }
            res.add(sub);
        }
        return res;
    }

    // 回溯法
    List<List<Integer>> resList = new LinkedList<>();

    public List<List<Integer>> subsets2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return resList;
        }
        List<Integer> path = new LinkedList<>();
        backtrack(nums, path, 0);
        return resList;
    }

    public void backtrack(int[] nums, List<Integer> path, int start) {
        resList.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 做出选择
            path.add(nums[i]);
            // 递归进入下一层，注意start+1，标识下一个选择列表的开始位置，最重要的一步
            backtrack(nums, path, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

}
