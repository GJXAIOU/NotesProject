package list.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 15:39
 */
public class Offer24 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
