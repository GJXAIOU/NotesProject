package string.easy;

import static java.lang.Character.isLetter;

/**
 * @author GJXAIOU
 * @create 2019-08-28-14:09
 */
public class LeetCode125_Valid_Palindrome {
    public boolean isPalindrome(String s) {
        if (s.trim().length() == 0 || s.trim() == null || s.trim().length() == 1) {
            return true;
        }
        // 1.全部转换为小写字符
        String inputString = s.toLowerCase();

        int left = 0;
        int right = inputString.length() - 1;
        while (left <= right) {
            while (!isLetter(inputString.charAt(left)) && left < inputString.length() - 1 ) {
                left++;
            }
            while (!isLetter(inputString.charAt(right)) && right > 0) {
                right--;
            }
            boolean equal = (inputString.charAt(left) == inputString.charAt(right));
            if (equal) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
