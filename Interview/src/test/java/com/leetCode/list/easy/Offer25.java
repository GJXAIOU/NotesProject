package list.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 15:47
 */
public class Offer25 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 新建一个链表头
        ListNode preHead = new ListNode(0);
        ListNode head = preHead;
        // 比较两个值相等，哪个小哪个跟在链表后面
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        // 两个总得有一个剩余
        while (l1 != null) {
            head.next = l1;
            l1 = l1.next;
            head = head.next;
        }
        while (l2 != null) {
            head.next = l2;
            l2 = l2.next;
            head = head.next;
        }

        return preHead.next;
    }
}
