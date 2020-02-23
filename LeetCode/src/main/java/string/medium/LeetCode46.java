package string.medium;

import javax.lang.model.type.ReferenceType;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 17:59
 */
public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length == 0) {
            return resList;
        }

        sort(nums, nums.length, 0, resList);
        return resList;

    }

    public void sort(int[] nums, int length, int usedCount, List<List<Integer>> resList) {
        List<Integer> tempList = new ArrayList<>(length);

        // 如果下标对应的值为 -1，则 nums 中同下标对应的值已经被使用了
        int[] usedNum = new int[nums.length];
        if (usedCount == length) {
            resList.add(new ArrayList<>(tempList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (usedNum[i] != -1){
                tempList.add(nums[i]);
                usedNum[i] = -1;
                sort(nums,length,usedCount + 1,resList);
                usedNum[i] = 0;
            }
        }
    }
}
