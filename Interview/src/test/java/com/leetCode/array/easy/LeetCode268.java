package array.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author GJXAIOU
 * @Date 2020/2/2 14:15
 */
public class LeetCode268 {
    // 方法一：排序
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        // 判断是否是开头或者结尾缺失
        if (nums[0] != 0) {
            return 0;
        }
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }

        // 判断中间哪位缺失
        for (int i = 1; i < nums.length; i++) {
            int correctNum = nums[i - 1] + 1;
            if (correctNum != nums[i]) {
                return correctNum;
            }
        }
        // 上面代码方式二：
//        int correctNum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (correctNum != nums[i]) {
//                return correctNum;
//            }
//            correctNum++;
//        }

        return 0;
    }

    // 方法二：使用 HashSet
    public int missingNumber2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    // 方法三：使用和
    public int missingNumber3(int[] nums){
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            sum -= nums[i - 1];
        }
        return sum;
    }
}
