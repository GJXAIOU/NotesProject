package math.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/5 11:50
 */
public class LeetCode69 {

    public int mySqrt(int x) {
        // 注意：针对特殊测试用例，例如 2147395599，因此要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long left = 0;
        // # 为了照顾到 1 把右边界设置为 x / 2 + 1
        long right = x / 2 + 1;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // 下面代码等价于：long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;
    }
}
