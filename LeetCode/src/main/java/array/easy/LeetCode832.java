package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 22:13
 */
public class LeetCode832 {
    // 方法一：位运算
    public int[][] flipAndInvertImage(int[][] A) {
        int column = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < column / 2; j++) {
                if (A[i][j] == A[i][column - j - 1]) {
                    A[i][j] ^= 1;
                    A[i][column - j - 1] ^= 1;
                }
            }
            // 如果是奇数列，补充计算中间元素
            if (column % 2 == 1) {
                A[i][column / 2] ^= 1;
            }
        }
        return A;
    }
}
