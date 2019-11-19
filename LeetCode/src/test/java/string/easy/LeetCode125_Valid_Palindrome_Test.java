package string.easy;

import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2019-08-28-14:09
 */
public class LeetCode125_Valid_Palindrome_Test {
    public static void main(String[] args) {
        LeetCode125_Valid_Palindrome leetCode125 = new LeetCode125_Valid_Palindrome();
        String sourceString = new Scanner(System.in).nextLine();
        boolean palindrome = leetCode125.isPalindrome(sourceString);
        System.out.println(palindrome);
    }
}
