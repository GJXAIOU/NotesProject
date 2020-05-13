package math.easy;

import java.util.concurrent.ForkJoinPool;

/**
 * @author GJXAIOU
 * @create 2020/05/13 13:36
 */
public class LeetCode1025 {
    public boolean divisorGame(int N) {
        if (N <= 0) {
            return false;
        }
        return N % 2 == 0;
    }

    // 方法二：DP
    public boolean divisorGame2(int N) {
        if (N <= 1) {
            return false;
        }
        // 枚举在 n 之前的每一种情况
        boolean[] res = new boolean[N + 1];
        // 当 N 是 1 的时候，爱丽丝先拿，没有元素可拿，输。
        res[1] = false;
        // 当 N 是 2 的时候，爱丽丝只能拿 1，N 剩下 1；bob 没办法拿，所以爱丽丝赢
        res[2] = true;
        // 递推后面的情况
        if (N >= 3) {
            for (int i = 3; i < res.length; i++) {
                // N % x == 0表明x一定是N的约数，所以最大的情况就是当 N为偶数，偶数的一半；N为奇数，奇数的一半向下取整
                for (int j = 1; j < i / 2; j++) {
                    // 可以拿 j，并且剩余的结果为假（因为剩余的是 Bob 先抽）
                    if ((i % j == 0 && res[i - j] == false)) {
                        res[i] = true;
                        break;
                    }
                }
            }
        }
        return res[N];
    }
}
