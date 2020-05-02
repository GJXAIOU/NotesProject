package array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 10:49
 */
public class LeetCode54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix == null) {
            return new ArrayList<>();
        }
        List<Integer> resList = new ArrayList<>();
        // 设置初始化左上角和右上角位置，从最外圈往里依次打印
        int leftTopRow = 0;
        int leftTopColumn = 0;
        int rightBottomRow = matrix.length - 1;
        int rightBottomColumn = matrix[0].length - 1;
        while (leftTopRow <= rightBottomRow && leftTopColumn <= rightBottomColumn) {
            resList.addAll(printEdge(matrix, leftTopRow++, leftTopColumn++, rightBottomRow--,
                    rightBottomColumn--));
        }
        return resList;
    }

    /**
     * 主要实现根据左上角和右下角的坐标，实现顺时针打印矩阵一圈的方法
     *
     * @param matrix
     * @param leftTopRow              左上角的行
     * @param leftTopColumn           左上角的列
     * @param rightBottomRow：右下角的行
     * @param rightBottomColumn：右下角的列
     */
    public List<Integer> printEdge(int[][] matrix, int leftTopRow, int leftTopColumn,
                                   int rightBottomRow
            , int rightBottomColumn) {
        List<Integer> edgeList = new ArrayList<>();
        // 如果 Matrix 只有一行
        if (leftTopRow == rightBottomRow) {
            for (int i = leftTopColumn; i <= rightBottomColumn; i++) {
                edgeList.add(matrix[leftTopRow][i]);
            }
            // 如果 Matrix 只有一列
        } else if (leftTopColumn == rightBottomColumn) {
            for (int i = leftTopRow; i <= rightBottomRow; i++) {
                edgeList.add(matrix[i][leftTopColumn]);
            }
        } else {
            // 打印四条边
            int curR = leftTopRow;
            int curC = leftTopColumn;
            while (curC != rightBottomColumn) {
                edgeList.add(matrix[leftTopRow][curC]);
                curC++;
            }
            while (curR != rightBottomRow) {
                edgeList.add(matrix[curR][rightBottomColumn]);
                curR++;
            }
            while (curC != leftTopColumn) {
                edgeList.add(matrix[rightBottomRow][curC]);
                curC--;
            }
            while (curR != leftTopRow) {
                edgeList.add(matrix[curR][leftTopColumn]);
                curR--;
            }
        }
        return edgeList;
    }
}
