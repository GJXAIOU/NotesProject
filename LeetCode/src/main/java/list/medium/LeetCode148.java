package list.medium;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 20:33
 */
public class LeetCode148 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 上面遍历结束之后，Slow 位于链表中点，记录中点，并把中点的 next 指向 null.
        ListNode temp = slow.next;
        slow.next = null;

        // 分别将左右两边排序
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        // merge 过程
        ListNode nodes = new ListNode(0);
        ListNode res = nodes;
        while (left != null && right != null) {
            if (left.val < right.val) {
                nodes.next = left;
                left = left.next;
            } else {
                nodes.next = right;
                right = right.next;
            }
            nodes = nodes.next;
        }
        nodes.next = left != null ? left : right;
        return res.next;
    }
}
