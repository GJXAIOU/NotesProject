package array.easy;

import javax.swing.plaf.SliderUI;

/**
 * @Author GJXAIOU
 * @Date 2020/2/20 9:43
 */
public class LeetCode1351 {

    // 方法一：二分查找
    public int countNegatives(int[][] grid) {
        int num = 0;
        for (int[] ints : grid) {
            int left = 0;
            int right = grid[0].length - 1;
            int pos = -1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (ints[mid] < 0) {
                    pos = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // pos=-1表示这一行全是>=0的数，不能统计
            if (pos != -1) {
                num += grid[0].length - pos;
            }
        }
        return num;
    }


    // 方法二：分治
    public int countNegatives2(int[][] grid) {
        return solve(0, grid.length - 1, 0, grid[0].length - 1, grid);
    }

    public int solve(int rowLeft, int rowRight, int columnLeft, int columnRight, int[][] grid) {
        if (rowLeft > rowRight) {
            return 0;
        }
        int mid = (rowLeft + rowRight) >>> 1;
        int pos = -1;
        for (int i = columnLeft; i <= columnRight; i++) {
            if (grid[mid][i] < 0) {
                pos = i;
                break;
            }
        }
        int res = 0;
        if (pos != -1) {
            res += grid[0].length - pos;
            res += solve(rowLeft, mid - 1, pos, columnRight, grid);
            res += solve(mid + 1, rowRight, columnLeft, pos, grid);
            // 说明[l..o-1]不会有负数，不用再去递归
        } else {
            res += solve(mid + 1, rowRight, columnLeft, columnRight, grid);
        }
        return res;
    }

    // 方法三：倒序遍历
    public int countNegatives3(int[][] grid) {
        int num = 0;
        int m = grid[0].length;
        int pos = grid[0].length - 1;
        for (int[] ints : grid) {
            int i;
            for (i = pos; i >= 0; i--) {
                if (ints[i] >= 0) {
                    if (i + 1 < m) {
                        pos = i + 1;
                        num += m - pos;
                    }
                    break;
                }
            }
            if (i == -1) {
                num += m;
                pos = -1;
            }
        }
        return num;
    }
}
