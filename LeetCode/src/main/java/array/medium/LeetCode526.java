package array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/06 16:37
 */
public class LeetCode526 {
    int res = 0;

    public int countArrangement(int N) {
        if (N < 1) {
            return 0;
        }
        List<Integer> path = new ArrayList<>();

        int[] num = new int[N + 1];
        for (int i = 0; i < num.length; i++) {
            num[i] = i;
        }

        boolean[] used = new boolean[N + 1];
        backTrack(num, used, path);
        return result;
    }

    public void backTrack(int[] num, boolean[] used, List<Integer> path) {
        if (path.size() == num.length - 1) {
            result++;
            return;
        }

        // 选择列表
        for (int i = 1; i < num.length; i++) {
            if (!used[i]) {
                // 剪枝，如果要放置的下一位 path.size() + 1 和 i 不能互相除
                if ((path.size() + 1) % i != 0 && i % (path.size() + 1) != 0) {
                    continue;
                }
                // 做选择
                path.add(num[i]);
                used[i] = true;
                // 进入下一轮
                backTrack(num, used, path);
                // 撤销选择
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }


    // 简化版本
    int result = 0;

    public int countArrangement2(int N) {
        boolean[] flag = new boolean[N + 1];
        helper(flag, N, 1);
        return result;
    }

    // count 表示已经排列到第几个数
    private void helper(boolean[] flag, int N, int count) {
        if (count == N + 1) {
            result++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            //如果当前数已经使用过
            if (flag[i]) {
                continue;
            }
            //剪枝条件：如果不能被i或整除i
            if (i % count != 0 && count % i != 0) {
                continue;
            }
            flag[i] = true;
            helper(flag, N, count + 1);
            flag[i] = false;
        }
    }
}
