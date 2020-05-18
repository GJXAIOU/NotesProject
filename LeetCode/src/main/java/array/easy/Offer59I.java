package array.easy;

import java.util.LinkedList;

/**
 * @author GJXAIOU
 * @create 2020/04/13 21:06
 */
public class Offer59I {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return new int[0];
        }

        LinkedList<Integer> maxList = new LinkedList<Integer>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // 更新双端队列，如果双端队列不为空，并且尾结点(存的是下标)对应数组中的值是否小于等于当前值
            while (!maxList.isEmpty() && nums[maxList.peekLast()] <= nums[i]) {
                maxList.pollLast();
            }
            // 上面一直弹出，直到不符合然后加上当前值。
            maxList.addLast(i);
            // 上面加法是通用的，但是减法是针对该题定制的
            // 当过期的时候（当窗口形成之后再扩充才算过期）即窗口长度 > k，窗口形成过程中不会过期, i - k 表示过期的下标
            if (maxList.peekFirst() == i - k) {
                maxList.pollFirst();
            }
            // 判断下标过期
            if (i >= k - 1) {
                // 当窗口已经形成了，记录每一步的res
                res[index++] = nums[maxList.peekFirst()];
            }
        }
        return res;
    }
}
