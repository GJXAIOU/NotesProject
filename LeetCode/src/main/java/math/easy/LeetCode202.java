package math.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 14:48
 */
public class LeetCode202 {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            // 慢指针走一步，快指针走两步，因为如果结果为1，则 fast 首先得到为 1，并且后续值均为 1。
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);

        return slow == 1;
    }

    int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }

    // 方法二：
    public boolean isHappy2(int n) {
        while (true) {
            if (n < 10) {
                if (n == 1 || n == 7) {
                    return true;
                } else {
                    return false;
                }
            }
            int sum = 0;
            while (n != 0) {
                sum = sum + (n % 10) * (n % 10);
                n = n / 10;
            }//while
            n = sum;
        }
    }
}
