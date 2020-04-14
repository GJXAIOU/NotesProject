package array.medium;

import java.util.Arrays;

/**
 * @author GJXAIOU
 * @create 2020/04/13 22:13
 */
public class Offer56I {
    public int[] singleNumbers(int[] nums) {
        // xorNumber为对整个数组求异或，用于求出分组条件。
        int xorNumber = nums[0];
        for (int k = 1; k < nums.length; k++) {
            xorNumber ^= nums[k];
        }

        // onePosition表示最低位1的位置的数，n&-n是求一个二进制数的最低位的1对应的数。（除其所在最低位为1，其他位为0的一个数）
        int onePosition = xorNumber & (-xorNumber);
        int ans1 = 0, ans2 = 0;
        // 最后利用onePosition作为划分条件，将数组分成两个数组，最终求得ans1与ans2的值。

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & onePosition) == onePosition) {
                ans1 ^= nums[i];
            } else {
                ans2 ^= nums[i];
            }
        }
        //因为ans1与ans2的初始值为0，最终结果也应当异或一下0，不过由于0异或任何数都是，其本身，因此有没有都可以。
        return new int[]{ans1, ans2};
    }


}
