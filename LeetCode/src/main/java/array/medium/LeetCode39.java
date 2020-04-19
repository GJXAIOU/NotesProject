package array.medium;

import java.util.*;

/**
 * @author GJXAIOU
 * @create 2020/04/19 20:46
 */
public class LeetCode39 {
    // 方法一：加法
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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

            tempQueue.addLast(candidates[i]);
            dfs(candidates, i, tempQueue, resList, sum + candidates[i], target);
            tempQueue.removeLast();
        }

    }


    // 方法二：减法
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        int len = candidates.length;

        // 排序是为了提前终止搜索
        Arrays.sort(candidates);

        dfs(candidates, len, target, 0, new ArrayDeque<>(), resList);
        return resList;
    }

    /**
     * @param candidates   数组输入
     * @param len          输入数组的长度，冗余变量
     * @param remainTarget 剩余数值
     * @param begin        本轮搜索的起点下标
     * @param path         从根结点到任意结点的路径
     * @param res          结果集变量
     */
    private void dfs(int[] candidates, int len, int remainTarget, int begin, Deque<Integer> path,
                     List<List<Integer>> res) {
        if (remainTarget == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 在数组有序的前提下，剪枝
            if (remainTarget - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, len, remainTarget - candidates[i], i, path, res);
            path.removeLast();

        }
    }
}
