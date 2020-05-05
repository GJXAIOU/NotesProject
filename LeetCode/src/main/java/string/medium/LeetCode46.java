package string.medium;

import javax.lang.model.type.ReferenceType;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 17:59
 */
public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length == 0) {
            return resList;
        }

        sort(nums, nums.length, 0, resList);
        return resList;

    }

    public void sort(int[] nums, int length, int usedCount, List<List<Integer>> resList) {
        List<Integer> tempList = new ArrayList<>(length);

        // 如果下标对应的值为 -1，则 nums 中同下标对应的值已经被使用了
        int[] usedNum = new int[nums.length];
        if (usedCount == length) {
            resList.add(new ArrayList<>(tempList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (usedNum[i] != -1) {
                tempList.add(nums[i]);
                usedNum[i] = -1;
                sort(nums, length, usedCount + 1, resList);
                usedNum[i] = 0;
            }
        }
    }


    // 方法二：
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> permute2(int[] nums) {

        if (nums.length == 0) {
            return resList;
        }
        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backtrack(nums, used, path);
        return resList;
    }

    void backtrack(int[] nums, boolean[] used, List<Integer> path) {
        if (path.size() == nums.length) {
            resList.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 从给定的数中除去用过的，就是当前的选择列表
            if (!used[i]) {
                // 做选择
                path.add(nums[i]);
                // 设置当前数已经使用过
                used[i] = true;
                // 进入下一层
                backtrack(nums, used, path);
                used[i] = false;
                path.remove(path.size() - 1);

            }
        }
    }
}

