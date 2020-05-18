package array.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 14:50
 */
public class LeetCode20 {
    public boolean isValid(String s) {
        // 首先使用 HashMap 保存括号的匹配规则
        HashMap<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // 判断该元素是否为三种右边括号之一，如果是说明是右边括号，需要弹出栈顶作为匹配
            if (map.containsKey(cur)) {
                // 如果栈不为空则弹出栈顶，否则随便弹出一个值
                char topElement = stack.empty() ? '#' : stack.pop();
                // 栈顶元素（左边括号）是否等于 map 中对应的左边括号
                if (topElement != map.get(cur)) {
                    return false;
                }
                // 如果不包含该 key，说明是左边括号，压入栈中
            } else {
                stack.push(cur);
            }
        }
        // 最后栈应该是空的
        return stack.isEmpty();
    }
}
