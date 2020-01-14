package nowcoder.easy.day05;

/**
 * 岛问题
 *
 * @author GJXAIOU
 */
public class Islands {

    public static int countIslands(int[][] inputArray) {
        if (inputArray == null || inputArray[0] == null) {
            return 0;
        }
        // row 为行数，column 为列数
        int row = inputArray.length;
        int column = inputArray[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (inputArray[i][j] == 1) {
                    // 岛的数目 + 1
                    res++;
                    // 进入感染函数，将其连成一片的 1 全部改为 2
                    infect(inputArray, i, j, row, column);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] inputArray, int i, int j, int row, int column) {
        // 判断边界以及当前遍历到的结点值是否为 1；
        if (i < 0 || i >= row || j < 0 || j >= column || inputArray[i][j] != 1) {
            return;
        }
        inputArray[i][j] = 2;
        infect(inputArray, i + 1, j, row, column);
        infect(inputArray, i - 1, j, row, column);
        infect(inputArray, i, j + 1, row, column);
        infect(inputArray, i, j - 1, row, column);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m1));

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m2));
    }
}
