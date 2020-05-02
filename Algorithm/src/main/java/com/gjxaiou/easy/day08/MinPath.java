package com.gjxaiou.easy.day08;

public class MinPath {

    public static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    // i 表示当前位置的行号，j 表示当前位置的列号；函数表示从 (i，j) 出发到达最右下角位置，最小路径和是多少（并且返回）
    public static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        // 因为开始位置就是结尾，所以到（0,0） 位置时候就是结果了。
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0 && j != 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    // 对应的递归版本（这里属于枚举了）
    public static int walk(int[][] matrix, int i, int j) {
        // 如果达到最右下角元素，则该元素达到最右下角距离为最右下角的值
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }
        // 如果到达最底下一行，只能向右走，距离最右下角距离为右边元素距离最右下角元素的距离；
        if (i == matrix.length - 1) {
            return matrix[i][j] + walk(matrix, i, j + 1);
        }
        // 如果到达最右边一列
        if (j == matrix[0].length - 1) {
            return matrix[i][j] + walk(matrix, i + 1, j);
        }
        // 该点右边位置到最右下角的路径和
        int right = walk(matrix, i, j + 1);
        // 该点下边位置到最右下角的路径和
        int down = walk(matrix, i + 1, j);
        // 选择值比较小的那一个
        return matrix[i][j] + Math.min(right, down);
    }

    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        // 测试递归
        int walk = walk(m, 0, 0);
        System.out.println(walk);
    }
}
