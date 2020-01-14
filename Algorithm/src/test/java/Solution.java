/**
 * LeetCode:https://leetcode-cn.com/problems/number-of-islands/
 * @author GJXIAOU
 * @create 2020/1/14 0014 下午 3:50
 */
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1'){
                    res++;
                    infection(grid, i, j, row, column);
                }
            }
        }
        return res;
    }

    public void infection(char[][] inputArray, int i, int j, int row, int column){
        if (i < 0 || j < 0 || i >= row || j >= column || inputArray[i][j] != '1') {
            return;
        }
        inputArray[i][j] = '2';
        infection(inputArray,i - 1, j, row, column);
        infection(inputArray,i + 1, j, row, column);
        infection(inputArray,i, j - 1, row, column);
        infection(inputArray,i, j + 1, row, column);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] m1 = {{'0', '0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '0'},
                {'0', '1', '1', '1', '0', '0', '0', '1', '0'},
                {'0', '1', '1', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '1', '1', '0', '0'},
                {'0', '0', '0', '0', '1', '1', '1', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '0'},};

        System.out.println(solution.numIslands(m1));
    }
}