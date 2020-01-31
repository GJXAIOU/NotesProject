package list.medium;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/1/31 17:56
 */
public class LeetCode2 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        // carry 为进位数，值为 0 或 1
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 如果某一个链表中值为 null 就用 0 代替
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            // 求进位数字，将 sum % 10 值（个位值）作为当前值，将 sum / 10（十位值）作为进位值，带入下一次迭代。
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 如果运算完之后还有进位，则直接加上一个值为 1 的结点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}
