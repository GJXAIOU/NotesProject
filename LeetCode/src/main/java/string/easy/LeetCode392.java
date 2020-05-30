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
        int index = -1;
        for (int i = 0; i < sArray.length; i++) {
            index = t.indexOf(sArray[i], index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    // 方法二：双指针
    public boolean isSubsequence2(String s, String t) {
        int i = 0, j = 0;
        int sLength = s.length(), tLength = t.length();
        while (i < sLength && j < tLength) {
            if (s.indexOf(i) == t.indexOf(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == sLength;
    }
}
