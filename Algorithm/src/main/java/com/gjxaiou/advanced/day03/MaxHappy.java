package com.gjxaiou.advanced.day03;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {

    public static class Node {
        public int huo;
        public List<Node> nexts;

        public Node(int huo) {
            this.huo = huo;
            nexts = new ArrayList<>();
        }
    }

    public static class ReturnData {
        public int lai_huo;
        public int bu_lai_huo;

        public ReturnData(int lai_huo, int bu_lai_huo) {
            this.lai_huo = lai_huo;
            this.bu_lai_huo = bu_lai_huo;
        }
    }

    /**
     * 方法一：递归
     */
    public static int getMaxHuo(Node head) {
        ReturnData data = process(head);
        return Math.max(data.bu_lai_huo, data.lai_huo);
    }

    public static ReturnData process(Node head) {
        // 来的时候默认就是结果包含自己的
        int lai_huo = head.huo;
        int bu_lai_huo = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            Node next = head.nexts.get(i);
            ReturnData nextData = process(next);
            lai_huo += nextData.bu_lai_huo;
            bu_lai_huo += Math.max(nextData.lai_huo, nextData.bu_lai_huo);
        }
        return new ReturnData(lai_huo, bu_lai_huo);
    }


    /**
     * 方法二：动态规划
     * matrix 第一维代表直接上级，第二维代表活跃值
     */
    public static int maxHappy(int[][] matrix) {
        // dp[i][0]表示的是i作为父节点，它不来的时候的最大活跃度；
        // dp[i][1]表示的是i作为父节点，它来的时候的最大活跃度；
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        // 如果某行行数值 = 其上级值，则表示该值为根节点
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        // 标记当前结点已经做过父 也就是它的 dp 数组中两个值都算出来了，可以直接用
        visited[root] = true;
        // 初始化根节点来的时候的活跃度
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process(matrix, dp, visited, i);
                //当root来，则加上root的每个直接下级不来的时候的活跃度
                dp[root][1] += dp[i][0];
                //当root不来，则每个直接下级可以来也可以不来，加上较大的活跃度
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 8}, {1, 9}, {1, 10}};
        System.out.println(maxHappy(matrix));
    }
}
