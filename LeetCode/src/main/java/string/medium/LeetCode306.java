package string.medium;

/**
 * @author GJXAIOU
 * @create 2020/04/17 22:19
 */
public class LeetCode306 {

    // 方法一：DFS、回溯
    long[] value;

    public boolean isAdditiveNumber(String num) {
        char[] inputValue = num.toCharArray();
        value = new long[inputValue.length];
        return dfs(inputValue, 0, 0);
    }

    public boolean dfs(char[] chars, int start, int count) {
        if (start == chars.length) {
            // 到达终点，判断是否有至少 3 个数
            return count > 2;
        }
        long numValue = 0;
        // 用 len 来判断是否存在 01 这种情况
        int len = 0;
        for (int i = start; i < chars.length; i++) {
            numValue *= 10;
            numValue += chars[i] - '0';
            len++;
            // 出现了 01 这种情况，是不允许的，直接返回 false
            if (String.valueOf(numValue).length() < len) {
                return false;
            }
            value[count] = numValue;
            //如果当前的累加数个数小于 3 个，就不用判断是否满足构成累加数的要求
            if (count < 2) {
                if (dfs(chars, i + 1, count + 1)) {
                    return true;
                }
                continue;
            }
            //当前的累加数个数大于等于 3 个，需要判断是否满足条件
            if (value[count] == value[count - 1] + value[count - 2]) {
                if (dfs(chars, i + 1, count + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

}
