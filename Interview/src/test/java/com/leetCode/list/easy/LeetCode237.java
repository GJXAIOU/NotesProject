package list.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 14:06
 */
public class LeetCode237 {
    // 单向链表结构
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}


