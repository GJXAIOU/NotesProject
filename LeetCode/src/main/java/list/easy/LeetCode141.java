package list.easy;

import java.util.HashSet;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 16:58
 */
public class LeetCode141 {
    // 单链表结构
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 使用 Hash 表
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // 使用双指针
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            // 如果 fast 到最后都和 slow 不相等，则没有
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
