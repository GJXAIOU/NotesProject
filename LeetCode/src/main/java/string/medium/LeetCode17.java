package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 9:35
 */

public class LeetCode17 {
    List<String> resList = new ArrayList<>();
    HashMap<Character, String> valueMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return resList;
        }
        StringBuilder path = new StringBuilder();
        backTrack(digits, 0, path);
        return resList;
    }

    public void backTrack(String digits, int depth, StringBuilder path) {
        if (depth == digits.length()) {
            resList.add(path.toString());
            return;
        }
        //选择列表
        char temp = digits.charAt(depth);
        char[] charArr = valueMap.get(temp).toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            //做出选择
            path.append(charArr[i]);
            backTrack(digits, depth + 1, path);
            //撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }
}
