package array.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 11:38
 */
public class LeetCode136 {

    public int singleNumber(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, 2);
            } else {
                map.put(num, 1);
            }
        }
        // 上面循环另一种方式
//        for (Integer i : nums) {
//            Integer count = map.get(i);
//            count = count == null ? 1 : ++count;
//            map.put(i, count);
//        }

        for (Integer i : map.keySet()) {
            int count = map.get(i);
            if (count == 1) {
                return i;
            }
        }

        return -1;
    }


    // 方法二：使用异或运算
    public int singleNumber2(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        if (nums.length == 1){
            return nums[0];
        }

        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
