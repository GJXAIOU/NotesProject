package math.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 19:40
 */
public class LeetCode204 {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            // for(int i = 2; i * i <= n; i++){
            if (n % i == 0) {
                // 有其他整除因子
                return false;
            }
        }
        return true;
    }

    // 方法二：
    int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i <= n; i++) {
            if (isPrim[i]) {
                // i 的倍数不可能是素数了
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }
        return count;
    }
}
