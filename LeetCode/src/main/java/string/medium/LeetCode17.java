package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 9:35
 */

// 递归这里还是有问题
public class LeetCode17 {
    HashMap digitsMap = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] digitsArray = digits.toCharArray();
        // 从第 0 位开始
        return helper(digitsArray, 0);
    }

    public List<String> helper(char[] digitsArray, int beginIndex) {
        List<String> resList = new ArrayList<>();
        String res = "";
        // 递归结束条件，因为是排列问题，所以开始长度到最后一位即可以输出了
        if (res.length() == digitsArray.length) {
            resList.add(res);
        }
        res += helper(digitsArray, beginIndex + 1);
        return resList;
    }

}
