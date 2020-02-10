package array.medium;

import java.util.HashSet;

/**
 * @Author GJXAIOU
 * @Date 2020/2/10 14:41
 */
public class LeetCode73 {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix == null) {
            return;
        }

        // 设置两个 set 存储元素为 0 的行列号
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> columnSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    columnSet.add(j);
                }
            }
        }

        // 再次遍历，将上面 Set 中 [i][j] 行列值全部变为 0；
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowSet.contains(i) || columnSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 方法二：原地修改
    public void setZeroes2(int[][] matrix) {
        int MODIFIED = -1000000;
        int m = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // 将 i 所在行非零值全部改为 MODIFIED
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = MODIFIED;
                        }
                    }
                    // 将 j 所在列非零值全部改为 MODIFIED
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = MODIFIED;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == MODIFIED) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 方法三：原地修改
    public void setZeroes3(int[][] matrix) {
        if (matrix.length == 0 || matrix == null) {
            return;
        }
        // 因为 matrix[0][0] 无法区分第一行还是第一列，使用 boolean 值表示是否为第一列，同时列从 1 开始
        Boolean columnZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                columnZero = true;
            }
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 首先将不是第一行第一列赋值
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 然后判断是第一行还是第一列，还是都是
        if (matrix[0][0] == 0) {
            // 首先第一行全部赋值为 0
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        // 然后判断第一列是否要赋值为 0
        if (columnZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    public static void main(String[] args) {
        int[][] value = new int[][]{{1}, {0}};
        LeetCode73 leetCode73 = new LeetCode73();
        leetCode73.setZeroes3(value);
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                System.out.print(value[i][j] + " ");
            }
        }
    }
}
