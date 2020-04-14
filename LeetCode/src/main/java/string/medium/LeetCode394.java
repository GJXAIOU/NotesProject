package string.medium;

import java.util.LinkedList;

/**
 * @author GJXAIOU
 * @create 2020/04/14 20:52
 */
public class LeetCode394 {
    // 方法一：栈
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> multiStack = new LinkedList<>();
        LinkedList<String> resStack = new LinkedList<>();
        // 遍历整个字符串数组
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                multiStack.addLast(multi);
                resStack.addLast(res.toString());
                // multi 变 0，res 清空
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = multiStack.removeLast();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(resStack.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    // 方法二：递归
    public String decodeString2(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            else if (s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']')
                return new String[]{String.valueOf(i), res.toString()};
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }
        return new String[]{res.toString()};
    }

}
