package com.gjxaiou.advanced.day06;

/**
 * @Author GJXAIOU
 * @Date 2020/1/12 10:35
 */
public class Robot {
    /**
     * 暴力破解方式
     *
     * @param N：一共有             1 ~ N 的初始位置
     * @param curPosition：来到的位置
     * @param restSteps：可以走的步数
     * @param K：最终停留在的位置
     * @return 一共有多少中走法
     * 变量分析，初始位置和最终停留位置是确定的，所有可变参数为： curPosition 和 restSteps
     */
    public static int ways1(int N, int curPosition, int restSteps, int K) {
        // 取出一些不可能的情况
        if (N < 2 || curPosition < 1 || curPosition > N || restSteps < 0 || K < 1 || K > N) {
            return 0;
        }
        // 不剩下步数了，看是否停在了 K 位置
        if (restSteps == 0) {
            return curPosition == K ? 1 : 0;
        }
        int res = 0;
        // 只能往右走了
        if (curPosition == 1) {
            res = ways1(N, curPosition + 1, restSteps - 1, K);
            // 到达最右边了，只能往左走
        } else if (curPosition == N) {
            res += ways1(N, curPosition - 1, restSteps - 1, K);
        } else {
            res += ways1(N, curPosition + 1, restSteps - 1, K) + ways1(N, curPosition - 1,
                    restSteps - 1, K);
        }
        return res;
    }


    // DP
    public static int ways2(int N, int curPosition, int restSteps, int k) {
        int[][] dp = new int[N + 1][restSteps + 1];
        // 赋 base case 值
        dp[k][0] = 1;
        // 填表，这里是一列一列填，不是一行一行
        for (int j = 1; j <= restSteps; j++) {
            // j 从 1 开始，因为 0 列已经赋值结束了
            for (int i = 1; i <= N; i++) {
                if (i == 1) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else if (i == N) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
                }
            }
        }
        return dp[curPosition][restSteps];
    }


    public static void main(String[] args) {
        System.out.println(Robot.ways1(5, 2, 3, 3));
        System.out.println(Robot.ways2(5, 2, 3, 3));
    }
}
