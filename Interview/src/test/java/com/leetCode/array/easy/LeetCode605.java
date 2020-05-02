package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/29 17:16
 */
public class LeetCode605 {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            // 当前位置为零，前一位置不存在或者值也为零，后一个位置不存在或者值为零
            boolean beforeIsZero = i - 1 == -1 || flowerbed[i - 1] == 0;
            boolean afterIsZero = i + 1 == flowerbed.length || flowerbed[i + 1] == 0;
            if (flowerbed[i] == 0 && beforeIsZero && afterIsZero) {
                n--;
                flowerbed[i] = 1;
            }
        }
        return n <= 0;
    }
}
