package string.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 10:10
 */
public class LeeCode344 {
    // 方法一：双指针
    public void reverseString(char[] s) {
        if (s.length == 0) {
        }

        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }


    // 方法二：递归
    public void helper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper(s, left, right);
    }

    public void reverseString2(char[] s) {
        helper(s, 0, s.length - 1);
    }
}
