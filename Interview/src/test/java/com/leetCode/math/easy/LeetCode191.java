package math.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 11:16
 */
public class LeetCode191 {
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}
