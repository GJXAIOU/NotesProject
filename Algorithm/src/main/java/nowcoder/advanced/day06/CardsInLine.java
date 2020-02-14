package nowcoder.advanced.day06;

/**
 * @author GJXAIOU
 */
public class CardsInLine {
    // 方法一：暴力递归
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    public static int first(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return arr[beginIndex];
        }
        return Math.max(arr[beginIndex] + second(arr, beginIndex + 1, endIndex),
                arr[endIndex] + second(arr
                , beginIndex, endIndex - 1));
    }

    public static int second(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return 0;
        }
        return Math.min(first(arr, beginIndex + 1, endIndex), first(arr, beginIndex, endIndex - 1));
    }

    // 方法二：动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
