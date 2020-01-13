package nowcoder.easy.day03;

/**
 * @author GJXAIOU
 * 转圈打印矩阵
 */
public class PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix) {
        // 设置初始化左上角和右上角位置，从最外圈往里依次打印
        int leftTopRow = 0;
        int leftTopColumn = 0;
        int rightBottomRow = matrix.length - 1;
        int rightBottomColumn = matrix[0].length - 1;
        while (leftTopRow <= rightBottomRow && leftTopColumn <= rightBottomColumn) {
            printEdge(matrix, leftTopRow++, leftTopColumn++, rightBottomRow--, rightBottomColumn--);
        }
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
    public static void printEdge(int[][] matrix, int leftTopRow, int leftTopColumn, int rightBottomRow, int rightBottomColumn) {
        // 如果 Matrix 只有一行
        if (leftTopRow == rightBottomRow) {
            for (int i = leftTopColumn; i <= rightBottomColumn; i++) {
                System.out.print(matrix[leftTopRow][i] + " ");
            }
            // 如果 Matrix 只有一列
        } else if (leftTopColumn == rightBottomColumn) {
            for (int i = leftTopRow; i <= rightBottomRow; i++) {
                System.out.print(matrix[i][leftTopColumn] + " ");
            }
        } else {
            // 打印四条边
            int curR = leftTopRow;
            int curC = leftTopColumn;
            while (curC != rightBottomColumn) {
                System.out.print(matrix[leftTopRow][curC] + " ");
                curC++;
            }
            while (curR != rightBottomRow) {
                System.out.print(matrix[curR][rightBottomColumn] + " ");
                curR++;
            }
            while (curC != leftTopColumn) {
                System.out.print(matrix[rightBottomRow][curC] + " ");
                curC--;
            }
            while (curR != leftTopRow) {
                System.out.print(matrix[curR][leftTopColumn] + " ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(matrix);
    }
}
