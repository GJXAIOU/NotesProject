package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 13:04
 */
public class Offer17 {

    public int[] printNumbers(int n) {

        if (n == 0) {
            return new int[0];
        }
        // 求得 n 位数对应的整数最大值
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = maxValue * 10 + 9;
        }

        int[] resArray = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            resArray[i] = i + 1;
        }
        return resArray;
    }
}
