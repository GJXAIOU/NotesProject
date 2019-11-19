package array.medium;

import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2019-08-28-19:55
 */
public class LeetCode209Minimum_Size_Subarray_Sum_Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targeSum = scanner.nextInt();
        scanner.nextLine();
        String inputString = scanner.nextLine();
        String[] inputArray = inputString.split(" ");
        int[] numsArray = new int [inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numsArray[i] = Integer.parseInt(inputArray[i].trim());
        }

        int ans = new LeetCode209Minimum_Size_Subarray_Sum().minSubArrayLen(targeSum, numsArray);
        System.out.println(ans);
    }
}
