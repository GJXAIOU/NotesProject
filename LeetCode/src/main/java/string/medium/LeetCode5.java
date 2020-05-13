package string.medium;

/**
 * @author GJXAIOU
 * @create 2020/05/07 22:15
 */
public class LeetCode5 {
    public String longestPalindrome1(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int curLength = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                curLength++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                curLength++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                curLength = curLength + 2;
                left--;
                right++;
            }
            if (curLength > maxLen) {
                maxLen = curLength;
                maxStart = left;
            }
            curLength = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    // DP
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        // 最长回文串的起点
        int maxStart = 0;
        // 最长回文串的终点
        int maxEnd = 0;
        // 最长回文串的长度
        int maxLen = 1;

        boolean[][] dp = new boolean[strLen][strLen];

        for (int right = 1; right < strLen; right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        maxStart = left;
                        maxEnd = right;
                    }
                }

            }

        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
