package array.easy;

import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2019-08-27-20:53
 */
public class Leetcode167_Two_Sum_II_Input_array_is_sorted_Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int target = scanner.nextInt();
        String[] inputArray = inputString.split(" ");
        int[] numsArray = new int [inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numsArray[i] = Integer.parseInt(inputArray[i].trim());
        }
        int[] ansArray = new int [2];


        Leetcode167_Two_Sum_II_Input_array_is_sorted leetcode167 =
                new Leetcode167_Two_Sum_II_Input_array_is_sorted();
        ansArray = leetcode167.twoSum(numsArray, target);

        for (int i : ansArray) {
            System.out.println(i);
        }
    }
}
