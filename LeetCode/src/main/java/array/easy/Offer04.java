package array.easy;

/**
 * @Author:GJXAIOU
 */
public class Offer04 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;

        // 初始位置在左上角，如果该值比 target 大就向左移动，如果小就向下移动
        int i = 0;
        int j = column - 1;
        while ((i <= row - 1) && (j >= 0)) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

}

