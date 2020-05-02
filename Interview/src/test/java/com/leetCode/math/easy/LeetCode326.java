package math.easy;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 19:12
 */
public class LeetCode326 {
    public boolean isPowerOfThree(int n) {

        if (n <= 0) {
            return false;
        }
        int max = (int) pow(3, (int) (log(0x7fffffff) / log(3)));
        return (max % n == 0);
    }
}
