package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/8 17:25
 */
public class LeetCode48 {
    // 方法一：绕圈反转
    public static void rotate(int[][] matrix) {
        int leftTopRow = 0;
        int leftTopColumn = 0;
        int rightBottomRow = matrix.length - 1;
        int rightBottomColumn = matrix[0].length - 1;
        // 因为是正方形，因此只要考虑一个就行，同时不能 =
        while (leftTopRow < rightBottomRow) {
            rotateEdge(matrix, leftTopRow++, leftTopColumn++, rightBottomRow--,
                    rightBottomColumn--);
        }
    }

    public static void rotateEdge(int[][] matrix, int leftTopRow, int leftTopColumn,
                                  int rightBottomRow, int rightBottomColumn) {
        int times = rightBottomColumn - leftTopColumn;
        int tmp = 0;
        // i 相当于圈数，从0 ~ times -1;（times 为右下角和左上角列数差），因为只需要交换 times 次就可以完成全部交换
        // 这里的圈数是指每一层矩阵中每次矩阵元素交换次数，
        // 具体的交换为一次换四个（对应一边一个）
        for (int i = 0; i != times; i++) {
            // 保留最上面一行
            tmp = matrix[leftTopRow][leftTopColumn + i];
            // 最上面一行等于最左边一列
            matrix[leftTopRow][leftTopColumn + i] = matrix[rightBottomRow - i][leftTopColumn];
            // 最左边一列等于最下面一行
            matrix[rightBottomRow - i][leftTopColumn] =
                    matrix[rightBottomRow][rightBottomColumn - i];
            // 最下面一行等于最右边一列
            matrix[rightBottomRow][rightBottomColumn - i] =
                    matrix[leftTopRow + i][rightBottomColumn];
            // 最右边一列等于最上面保留的一行
            matrix[leftTopRow + i][rightBottomColumn] = tmp;
        }
    }

    // 方法二：两次翻转
    public void rotate2(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }
        int nums = matrix.length;
        for (int i = 0; i < nums; ++i) {
            // 首先左上角和右下角进行翻转
            for (int j = 0; j < nums - i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[nums - 1 - j][nums - 1 - i];
                matrix[nums - 1 - j][nums - 1 - i] = temp;
            }
        }
        for (int i = 0; i < (nums >> 1); ++i) {
            for (int j = 0; j < nums; ++j) {
                // 矩阵上下对折
                int temp = matrix[i][j];
                matrix[i][j] = matrix[nums - 1 - i][j];
                matrix[nums - 1 - i][j] = temp;
            }
        }
    }
}
