package list.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 15:30
 */
public class LeetCode160 {

    // 单链表结构
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 方法一：使用 Map
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (headA != null) {
            map.put(headA, headA.val);
            headA = headA.next;
        }

        while (headB != null) {
            if (map.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // 方法二：不使用 Map 结构，使用双指针
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // n 是两个链表差值关系
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 如果走到最后两个节点不相等，则两个链表不想交
        if (cur1 != cur2) {
            return null;
        }
        // 在定位哪一个是长链表，哪一个是短链表
        // cur1 指向长链表的头部，cur2 指向短链表的头部
        cur1 = n > 0 ? headA : headB;
        cur2 = cur1 == headA ? headB : headA;
        n = Math.abs(n);
        // 长的先走 n 步，然后短的再走，最后返回的 cur1 就是他们进入的第一个相交节点
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
