package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/21 21:30
 */
public class LeetCode283 {
    // 方法一：将非零元素移动到数组的前面，然后最后空余的部分全部补零。
    public void moveZeroes(int[] nums) {
        //i:插入位置下标 ; j:查找位置下标
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        //将后面的位置补0
        for (int p = i; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    // 方法二：边填充零位置边补零
    public void moveZeroes2(int[] nums) {
        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt, cur);
                lastNonZeroFoundAt++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        LeetCode283 leetCode283 = new LeetCode283();
        int[] value = {1};
        leetCode283.moveZeroes2(value);
        for (int i = 0; i < value.length; i++) {
            System.out.println(value[i] + " ");
        }
    }

}
