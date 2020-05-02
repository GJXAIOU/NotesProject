package string.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 13:47
 */
public class LeetCode242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alpha = new int[26];
        for (int i = 0; i < t.length(); i++) {
            // 类似于桶排序：t.charAt(i) - 'a' 的值为 0 ~ 25，该数组每一位上值对应着 a ~ z 出现次数
            alpha[t.charAt(i) - 'a']++;
            alpha[s.charAt(i) - 'a']--;
        }

        for (int i : alpha) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    // 方法二：排序
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray,tArray);
    }
}
