package string.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 15:06
 */
public class LeetCode131 {

    public List<List<String>> partition(String s) {
        List<List<String>> resList = new ArrayList<>();
        if (s.length() == 0) {
            return resList;
        }
        // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
        // 注意：只使用 stack 相关的接口
        Deque<String> stack = new ArrayDeque<>();
        split(s, s.length(), stack, 0, resList);
        return resList;
    }

    // 判断当前是否为回文串，如果是，继续划分，否则退出
    public void split(String s, int sLength, Deque<String> path, int beginIndex,
                      List<List<String>> resList) {
        List<String> tempList = new ArrayList<>(path);
        if (beginIndex == sLength) {
            resList.add(tempList);
            return;
        }

        for (int i = beginIndex; i < sLength; i++) {
            // 判断从 beginIndex 到 i 是否为回文串，如果是就将 i + 1 之后带入递归，如果不是就返回
            if (!judgeIfPalindrome(s, beginIndex, i)) {
                continue;
            }
            path.addLast(s.substring(beginIndex, i + 1));
            split(s, sLength, path, i + 1, resList);
            path.removeLast();
        }
    }

    // 判断是否为回文串
    public boolean judgeIfPalindrome(String s, int beginIndex, int endIndex) {
        while (beginIndex < endIndex) {
            if (s.charAt(beginIndex) != s.charAt(endIndex)) {
                return false;
            }
            beginIndex++;
            endIndex--;
        }
        return true;
    }
}
