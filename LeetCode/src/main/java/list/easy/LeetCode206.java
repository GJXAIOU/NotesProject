package list.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 14:35
 */
public class LeetCode206 {
    // 单链表结构
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            // 记录当前结点的下一个结点
            temp = cur.next;
            // 将当前结点的下一节点指向前一个结点
            cur.next = pre;
            // pre 和 cur 结点均向后移动
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
