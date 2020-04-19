package array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/19 18:07
 */
public class LeetCode22 {
    // 方法一：做减法
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs(n, n, "", res);
        return res;
    }

    /**
     * @param curStr      当前递归得到的结果
     * @param leftRemain  左括号还有几个可以使用
     * @param rightRemain 右括号还有几个可以使用
     * @param res         结果集
     */

    private void dfs(int leftRemain, int rightRemain, String curStr, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (leftRemain == 0 && rightRemain == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (leftRemain > rightRemain) {
            return;
        }

        if (leftRemain > 0) {
            dfs(leftRemain - 1, rightRemain, curStr + "(", res);
        }

        if (rightRemain > 0) {
            dfs(leftRemain, rightRemain - 1, curStr + ")", res);
        }
    }

    // 方法二：做加法
    public List<String> generateParenthesis2(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<String> resList = new ArrayList<>();
        dfs(0, 0, n, "", resList);
        return resList;
    }

    /**
     * @param curStr    当前递归得到的结果
     * @param leftUsed  左括号已经用了几个
     * @param rightUsed 右括号已经用了几个
     * @param total     左括号、右括号一共得用几个
     * @param resList   结果集
     */
    public void dfs(int leftUsed, int rightUsed, int total, String curStr, List<String> resList) {
        if ((leftUsed == total) && (leftUsed == total)) {
            resList.add(curStr);
            return;
        }
        if (leftUsed < rightUsed) {
            return;
        }

        if (leftUsed < total) {
            dfs(leftUsed++, rightUsed, total, curStr + "(", resList);
        }
        if (rightUsed < total) {
            dfs(leftUsed, rightUsed++, total, curStr + ")", resList);
        }
    }


}
