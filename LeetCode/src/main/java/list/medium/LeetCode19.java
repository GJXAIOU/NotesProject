package list.medium;


/**
 * @Author GJXAIOU
 * @Date 2020/1/31 15:39
 */

public class LeetCode19 {
    // 单链表结构
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 方法一：两次遍历
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 遍历获取链表长度
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        // 因为前面加了一个结点，所以需要删除的位置是 Length - n 位置。
        length -= n;
        cur = dummy;
        while (length > 0) {
            length--;
            cur = cur.next;
        }
        // 删除 cur.next 位置
        cur.next = cur.next.next;
        // 不能返回 head，防止是删除唯一结点结构的列表
        return dummy.next;
    }

    // 方法二：一次遍历
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 首先仍然增加一个结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 首先快指针先走 n+1 步
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 此时 slow 指向要删除节点的前一个节点
        slow.next = slow.next.next;
        return dummy.next;
    }
}
