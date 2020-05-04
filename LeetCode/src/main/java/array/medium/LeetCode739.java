package array.medium;

import java.util.Stack;

/**
 * @author GJXAIOU
 * @create 2020/05/03 16:11
 */
public class LeetCode739 {
    // 单调栈：压入元素下标
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        if (T.length == 0 || T == null) {
            return res;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
