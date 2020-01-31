package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/29 15:22
 */
public class LeetCode566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int column = nums[0].length;
        int[][] res = new int[r][c];
        int curColumn = 0;
        int curRow = 0;

        // 合法性判断
        if (row == 0 || column == 0 || r <= 0 || c <= 0 || row * column != r * c) {
            return nums;
        }
        // 遍历原数组，当原来元素下标大于r之后，行坐标加一
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // 如果列等于给定的列数目，行数+1
                if (curColumn == c) {
                    curRow++;
                    curColumn = 0;
                }
                res[curRow][curColumn] = nums[i][j];
                curColumn++;
            }
        }
        return res;
    }
}


