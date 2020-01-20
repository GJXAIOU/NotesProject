package array.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 14:50
 */
public class LeetCode20 {

    // 首先使用 HashMap 保存括号的匹配规则
    private HashMap<Character, Character> map;

    // 在构造函数中生成规则
    public LeetCode20() {
        map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 判断该元素是否为三种右边括号之一，如果是说明是右边括号，需要弹出栈顶作为匹配
            if (map.containsKey(c)) {
                // 如果栈不为空则弹出栈顶，否则随便弹出一个值
                char topElement = stack.empty() ? '#' : stack.pop();
                // 栈顶元素（左边括号）是否等于 map 中对应的左边括号
                if (topElement != map.get(c)) {
                    return false;
                }
                // 如果不包含该 key，说明是左边括号，压入栈中
            } else {
                stack.push(c);
            }
        }
        // 最后栈应该是空的
        return stack.isEmpty();
    }
}
