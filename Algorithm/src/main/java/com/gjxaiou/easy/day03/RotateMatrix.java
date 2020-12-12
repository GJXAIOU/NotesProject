package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 正方形矩阵顺时针翻转 90 度
 */
public class RotateMatrix {

    public static void rotate(int[][] matrix) {
        int leftTopRow = 0;
        int leftTopColumn = 0;
        int rightBottomRow = matrix.length - 1;
        int rightBottomColumn = matrix[0].length - 1;
        // 因为是正方形，因此只要考虑一个就行，同时不能 =
        while (leftTopRow < rightBottomRow) {
            rotateEdge(matrix, leftTopRow++, leftTopColumn++, rightBottomRow--, rightBottomColumn--);
        }
    }

    /**
     * @param matrix
     * @param leftTopRow     左上角的行
     * @param leftTopColumn  左上角的列
     * @param rightBottomRow：右下角的行
     * @param rightBottomColumn：右下角的列
     */
    public static void rotateEdge(int[][] matrix, int leftTopRow, int leftTopColumn, int rightBottomRow, int rightBottomColumn) {
        int times = rightBottomColumn - leftTopColumn;
        int tmp = 0;
        // i 相当于圈数，从0 ~ times -1;（times 为右下角和左上角列数差），因为只需要交换 times 次就可以完成全部交换
        // 这里的圈数是指每一层矩阵中每次矩阵元素交换次数，
        // 具体的交换为一次换四个（对应一边一个）
        for (int i = 0; i != times; i++) {
            tmp = matrix[leftTopRow][leftTopColumn + i];
            matrix[leftTopRow][leftTopColumn + i] = matrix[rightBottomRow - i][leftTopColumn];
            matrix[rightBottomRow - i][leftTopColumn] = matrix[rightBottomRow][rightBottomColumn - i];
            matrix[rightBottomRow][rightBottomColumn - i] = matrix[leftTopRow + i][rightBottomColumn];
            matrix[leftTopRow + i][rightBottomColumn] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
