package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/04 14:11
 */
public class LeetCode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 首先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }

        for (int begin = 0; begin <= nums.length - 4; begin++) {
            //确保nums[begin] 改变了
            if (begin > 0 && nums[begin] == nums[begin - 1]) {
                continue;
            }
            for (int start = begin + 1; start <= nums.length - 3; start++) {
                //确保nums[start] 改变了
                if (start > begin + 1 && nums[start] == nums[start - 1]) {
                    continue;
                }
                int move = start + 1;
                int end = nums.length - 1;
                while (move < end) {
                    int sum = nums[begin] + nums[start] + nums[move] + nums[end];
                    if (sum < target) {
                        move++;
                    } else if (sum > target) {
                        end--;
                    } else {
                        ArrayList<Integer> tempList = new ArrayList<>();
                        tempList.add(nums[begin]);
                        tempList.add(nums[start]);
                        tempList.add(nums[move]);
                        tempList.add(nums[end]);
                        res.add(tempList);
                        //确保nums[move] 改变了
                        while (move < end && nums[move] == nums[move + 1]) {
                            move++;
                        }
                        //确保nums[end] 改变了
                        while (move < end && nums[end] == nums[end - 1]) {
                            end--;
                        }
                        move++;
                        end--;
                    }
                }
            }
        }
        return res;
    }
}
