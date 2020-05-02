package array.easy;

import java.util.Random;

/**
 * @Author GJXAIOU
 * @Date 2020/1/18 21:47
 */
public class LeetCode07 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // 获取 X 的最后一位
            int temp = x % 10;
            x /= 10;

            if (res > Integer.MAX_VALUE / 10 || ((res == Integer.MAX_VALUE / 10) && (temp > 7))) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || ((res == Integer.MIN_VALUE / 10) && (temp < -8))) {
                return 0;
            }
            res = res * 10 + temp;
        }
        return res;
    }

    //---------- 对数器----------
    // 1. 绝对正确的方法
    public static int absoluteReverse(int x) {
        if (x == 0) {
            return x;
        }

        long result = 0;

        // 转为正数统一出处理，使用long类型处理溢出问题。
        long a = x > 0 ? x : -x;

        long pop;

        while (a > 0) {
            pop = a % 10;
            result = result * 10 + pop;
            a = a / 10;
        }
        result = x > 0 ? result : -result;

        if (result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE) {
            return (int) result;
        }
        return 0;
    }

    // 一个随机数产生器
    public int generateRandom() {
        Random random = new Random();
        int i = random.nextInt();
        return i;
    }

    // 一个比较器
    public boolean isEqual(int x, int y) {
        if (x == y) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode07 solution = new LeetCode07();
        int randomValue = solution.generateRandom();
        System.out.println(randomValue);
        int res1 = solution.reverse(randomValue);
        System.out.println(res1);
        System.out.println(randomValue);
        int res2 = absoluteReverse(randomValue);
        System.out.println(res2);
        boolean equal = solution.isEqual(res1, res2);
        System.out.println(equal);
    }
}
