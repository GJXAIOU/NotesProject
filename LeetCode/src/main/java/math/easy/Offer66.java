package math.easy;

/**
 * @author GJXAIOU
 * @create 2020/04/12 21:24
 */
public class Offer66 {
    public int[] constructArr(int[] a) {
        int[] b = new int[a.length];
        int leftMultiply = 1;
        int[] left = new int[a.length];
        int rightMultiply = 1;
        int[] right = new int[a.length];
        // 分别求出 i 值左边的乘积和右边乘积（均不包括 i 值）
        for (int i = 0; i < a.length; i++) {
            left[i] = leftMultiply;
            leftMultiply *= a[i];
        }
        for (int i = a.length - 1; i >= 0; i--) {
            right[i] = rightMultiply;
            rightMultiply *= a[i];

        }

        for (int i = 0; i < b.length; i++) {
            b[i] = left[i] * right[i];
        }
        return b;
    }

}
