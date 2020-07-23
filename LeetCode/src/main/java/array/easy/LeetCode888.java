package array.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author GJXAIOU
 * @create 2020/05/18 22:49
 */
public class LeetCode888 {

    // 方法一：方程求解
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int x : A) {
            sumA += x;
        }
        for (int x : B) {
            sumB += x;
        }

        Set<Integer> setB = new HashSet();
        for (int x : B) {
            setB.add(x);
        }

        for (int x : A) {
            if (setB.contains(x + (sumB - sumA) / 2)) {
                return new int[]{x, x + (sumB - sumA) / 2};
            }
        }
        return new int[2];
    }
}
