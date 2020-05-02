package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 15:26
 */

public class LeetCode21 {
    // 结点结构
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 新建一个链表
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 当两个链表都不为空的时候，那个值小哪个结点就放在 next 位置
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // l1 或者 l2 中间有一个是非空的，那个非空哪个接在后面
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}
