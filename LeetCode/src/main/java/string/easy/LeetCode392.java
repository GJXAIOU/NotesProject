package string.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/13 17:34
 */
public class LeetCode392 {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        // 这种变量放在循环外面定义，否认每次开始值都是 -1
        int j = -1;
        for (int i = 0; i < sArray.length; i++) {
            j = t.indexOf(sArray[i], j + 1);
            if (j == -1) {
                return false;
            }
        }
        return true;
    }

    // 方法二：双指针
    public boolean isSubsequence2(String s, String t) {
        int i = 0, j = 0;
        int m = s.length(), n = t.length();
        while (i < m && j < n) {
            if (s.indexOf(i) == t.indexOf(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == m;
    }
}
