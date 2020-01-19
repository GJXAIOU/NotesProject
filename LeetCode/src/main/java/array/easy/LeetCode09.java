package array.easy;

import java.util.Random;

/**
 * @Author GJXAIOU
 * @Date 2020/1/19 15:27
 */
public class LeetCode09 {
    // 解法一：转换为字符串，然后反转字符串比较
    public boolean isPalindrome(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    // 解法二：直接反转整数值
    public boolean isPalindrome2(int x) {
        // 边界判断，如果是负数直接返回 发了 false
        if (x < 0) {
            return false;
        }
        int div = 1;
        // 相当于获取 X 一共几位数，例如 x = 7324,则 div = 1000,在下面相除得到首位 7
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            // 获取首位和末尾
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    // 解法三：反转后半段值然后和前半段比较
    public boolean IsPalindrome3(int x) {
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，则其第一位数字也应该是 0,只有值为 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    // ---------对数器-------
    // 绝对正确的方法，三种方法都可以作为比较方法，不再单独实现

    // 生成随机值
    public static int generateRandom() {
        int randomValue = new Random().nextInt();
        return randomValue;
    }

    // 生成比较方法
    public static boolean isEquals(boolean x, boolean y, boolean z) {
        if ((x == y) && (x == z) && (y == z)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode09 leetCode09 = new LeetCode09();
        int randomValue = generateRandom();
        boolean palindrome = leetCode09.isPalindrome(randomValue);
        boolean palindrome2 = leetCode09.isPalindrome2(randomValue);
        boolean palindrome3 = leetCode09.IsPalindrome3(randomValue);
        System.out.println("randomValue: " + randomValue);
        System.out.println("palindrome: " + palindrome + ", palindrome2: " + palindrome2 +
                ", palindrome3: " + palindrome3);
        System.out.println(isEquals(palindrome, palindrome2, palindrome3));
    }
}
