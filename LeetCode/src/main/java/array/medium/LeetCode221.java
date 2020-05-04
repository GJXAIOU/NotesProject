package array.medium;

/**
 * @author GJXAIOU
 * @create 2020/05/04 15:20
 */
public class LeetCode221 {
    // 方法一：暴力方法
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, column = row > 0 ? matrix[0].length : 0;
        int maxSqlen = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    int squalLength = 1;
                    boolean flag = true;
                    // 正方形长或者宽有一方越界则停止
                    while (squalLength + i < row && squalLength + j < column && flag) {
                        for (int k = j; k <= squalLength + j; k++) {
                            if (matrix[i + squalLength][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        for (int k = i; k <= squalLength + i; k++) {
                            if (matrix[k][j + squalLength] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            squalLength++;
                        }
                    }
                    if (maxSqlen < squalLength) {
                        maxSqlen = squalLength;
                    }
                }
            }
        }
        return maxSqlen * maxSqlen;
    }

    // 方法二：DP
    public int maximalSquare2(char[][] matrix) {
        int row = matrix.length, column = row > 0 ? matrix[0].length : 0;
        int[][] dp = new int[row + 1][column + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

}
