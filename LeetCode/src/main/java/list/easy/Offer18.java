package list.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 10:51
 */
public class Offer18 {
    class ListNode {
        int value;
        ListNode next;

        ListNode(int x) {
            value = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        // 如果是头结点
        if (head.value == val) {
            return head.next;
        }

        // 找到要删除节点的前驱节点
        while (cur != null && (cur.value != val)) {
            pre = cur;
            cur = cur.next;
        }
        // 如果是最后一个，则将 pre.next 即最后一个结点置为 null
        if (cur.next == null) {
            pre.next = null;
            // 如果不是最后一个，跳过该结点
        } else {
            pre.next = cur.next;
        }
        return head;
    }
}
