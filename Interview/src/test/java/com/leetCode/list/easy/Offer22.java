package list.easy;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 13:18
 */
public class Offer22 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        // 快指针先跑 K 步
        while (k != 0) {
            fast = fast.next;
            k--;
        }
        // 快慢指针一起跑
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
