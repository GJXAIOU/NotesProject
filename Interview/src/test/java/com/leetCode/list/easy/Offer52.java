package list.easy;


/**
 * @Author GJXAIOU
 * @Date 2020/2/28 12:29
 */
public class Offer52 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        ListNode cur2 = headB;

        // count 是两个链表差值关系
        int count = 0;
        while (cur1 != null) {
            count++;
            cur1 = cur1.next;
        }

        while (cur2 != null) {
            count--;
            cur2 = cur2.next;
        }
        // 如果走到最后两个节点不相等，则两个链表不想交
        if (cur1 != cur2) {
            return null;
        }

        // 在定位哪一个是长链表，哪一个是短链表
        // cur1 指向长链表的头部，cur2 指向短链表的头部
        cur1 = count > 0 ? headA : headB;
        cur2 = cur1 == headA ? headB : headA;
        count = Math.abs(count);

        // 长的先走 count 步，然后短的再走，最后返回的 cur1 就是他们进入的第一个相交节点
        while (count != 0) {
            count--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {

            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 方法二：
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        while (cur1 != cur2) {
            cur1 = cur1 == null ? headB : cur1.next;
            cur2 = cur2 == null ? headA : cur2.next;
        }
        return cur1;
    }

}
