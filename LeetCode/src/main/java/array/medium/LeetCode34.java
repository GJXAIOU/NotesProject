package array.medium;

/**
 * @Author GJXAIOU
 * @Date 2020/2/11 11:16
 */
public class LeetCode34 {


    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
// 首先搜索左区间
        int leftIndex = extremeInsertionIndex(nums, target, true);

        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return targetRange;
        }

        targetRange[0] = leftIndex;
        // 然后搜索右区间
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

        return targetRange;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean leftFind) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            // 当目标值小于中间值，或者目标值等于中间值且 leftFind 为 true，则搜索左边
            if (nums[mid] > target || (leftFind && target == nums[mid])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 如果一直在左区间搜索，直到 left 和 right 相等
        return left;
    }


    // 方案二：线性扫描
    public int[] searchRange2(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        if (targetRange[0] == -1) {
            return targetRange;
        }

        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
