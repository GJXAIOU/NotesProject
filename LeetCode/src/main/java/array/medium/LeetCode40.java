package array.medium;

import java.util.*;

/**
 * @author GJXAIOU
 * @create 2020/04/19 22:22
 */
public class LeetCode40 {
    // 方法一：加数
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        // 因为 candidates 元素都是大于 0
        if (candidates.length == 0 || target <= 0) {
            return resList;
        }

        Arrays.sort(candidates);
        dfs(candidates, 0, new ArrayDeque<>(), resList, 0, target);
        return resList;
    }

    /**
     * @param candidates 候选数组
     * @param index      已经遍历到的位置
     * @param resList    结果
     * @param sum        到当前位置的所有和
     * @param target
     */
    public void dfs(int[] candidates, int index, Deque<Integer> tempQueue,
                    List<List<Integer>> resList, int sum, int target) {
        if (sum == target) {
            resList.add(new ArrayList<>(tempQueue));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target - sum) {
                break;
            }

            // 小剪枝
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tempQueue.addLast(candidates[i]);
            dfs(candidates, i + 1, tempQueue, resList, sum + candidates[i], target);
            tempQueue.removeLast();
        }

    }


    // 方法二：减数
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        // 新建最终结果，放入 dfs 中
        List<List<Integer>> resList = new ArrayList<>();
        if (candidates.length == 0) {
            return resList;
        }

        // 先将数组排序，这一步很关键
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>(candidates.length);
        dfs(candidates, 0, target, path, resList);
        return resList;
    }

    /**
     * @param candidates   候选数组
     * @param begin        从候选数组的 begin 位置开始搜索
     * @param remainTarget 表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path         从根结点到叶子结点的路径
     * @param res
     */
    private void dfs(int[] candidates, int begin, int remainTarget, Deque<Integer> path,
                     List<List<Integer>> res) {
        if (remainTarget == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 大剪枝
            if (remainTarget - candidates[i] < 0) {
                break;
            }

            //////// 主要是加上这里和 下面 dfs 中开始位置从 i 变为 i + 1 //////
            // 小剪枝
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, i + 1, remainTarget - candidates[i], path, res);

            path.removeLast();
        }
    }


}

