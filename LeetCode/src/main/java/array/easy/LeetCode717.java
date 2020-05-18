package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 21:10
 */
public class LeetCode717 {
    public boolean isOneBitCharacter(int[] bits) {
        int start = 0;
        while (start < bits.length - 1) {
            if (bits[start] == 0) {
                start++;
            } else {
                start += 2;
            }
        }
        return start == bits.length - 1;
    }
}
