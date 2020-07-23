package array.easy;

import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020-07-13 22:54
 */
public class OR176 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        int[] input = new int[count];
        int i = 0;
        while (count > 0) {
            input[i++] = scanner.nextInt();
            count--;
        }
        System.out.println(maxSum(input));
    }

    // 上一个元素作为最后一个元素的所有连续子数组的最大和是多少，只要它不是负数，那么此问题就是它加上最后一个元素的值，否则直接用最后一个元素的值即可
    public static int maxSum(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

