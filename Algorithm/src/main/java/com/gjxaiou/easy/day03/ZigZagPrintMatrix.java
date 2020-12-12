package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 之字形打印矩阵中所有元素
 */
public class ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int leftTopRow = 0;
        int leftTopColumn = 0;
        int rightBottomRow = 0;
        int rightBottomColumn = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;
        boolean fromUp = false;
        while (leftTopRow != endRow + 1) {
            printLevel(matrix, leftTopRow, leftTopColumn, rightBottomRow, rightBottomColumn, fromUp);
            // 如果 A点来到最后一列，就往下走，否则行号不变
            leftTopRow = (leftTopColumn == endColumn) ? leftTopRow + 1 : leftTopRow;
            leftTopColumn = (leftTopColumn == endColumn) ? leftTopColumn : leftTopColumn + 1;
            rightBottomColumn = (rightBottomRow == endRow) ? rightBottomColumn + 1 : rightBottomColumn;
            rightBottomRow = (rightBottomRow == endRow) ? rightBottomRow : rightBottomRow + 1;
            // 每打印一次，打印方向翻转一次
            fromUp = !fromUp;
        }
        System.out.println();
    }

    // 打印对角线
    public static void printLevel(int[][] m, int leftTopRow, int leftTopColumn, int rightBottomRow, int rightBottomColumn,
                                  boolean fromUp) {
        if (fromUp) {
            // 从上往下打印对角线
            while (leftTopRow != rightBottomRow + 1) {
                System.out.print(m[leftTopRow++][leftTopColumn--] + " ");
            }
        } else {
            // 从下往上打印对角线
            while (rightBottomRow != leftTopRow - 1) {
                System.out.print(m[rightBottomRow--][rightBottomColumn++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);
    }
}
