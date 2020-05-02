package array.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.XMLFormatter;

/**
 * @Author GJXAIOU
 * @Date 2020/1/21 16:42
 */
public class LeetCode217 {
    // 方法一：使用 HashMap 进行判断，每次放入元素判断是否存在
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    // 方法二：使用 HashSet 进行判断
    public boolean containsDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return nums.length == set.size() ? false : true;
    }

    // -------------对数器------------------
    // 绝对正确的方法
    public boolean absoluteContainsDuplicate(int[] nums) {
        //可以先排序，然后遍历即可

        int length = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // 随机数产生器
    public static int[] generateRandom(int length, int maxValue) {
        int[] randomValue = new int[length];
        for (int i = 0; i < randomValue.length; i++) {
            randomValue[i] = (int) (Math.random() * maxValue);
        }
        return randomValue;
    }

    // 比较方法
    public static boolean isEquals(boolean x, boolean y, boolean z) {
        if (x != y || x != z || y != z) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] ints = generateRandom(40, 100);
        LeetCode217 leetCode217 = new LeetCode217();
        boolean i = leetCode217.containsDuplicate(ints);
        boolean j = leetCode217.containsDuplicate2(ints);
        boolean z = leetCode217.absoluteContainsDuplicate(ints);
        boolean equals = isEquals(i, j, z);
        System.out.println(equals);
    }
}
