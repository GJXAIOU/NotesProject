package list.medium;


/**
 * @Author GJXAIOU
 * @Date 2020/1/31 11:54
 */
public class LeetCode328 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 思路：将奇偶位节点分别形成链表，然后偶链表接在奇链表之后
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        // even 结点不断遍历，最后合并时候需要 even 链表的头结点，所以这是使用 evenHead 记录；
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;


        while (even != null && even.next != null) {
            // 形成奇数节点链表
            odd.next = even.next;
            odd = odd.next;
            // 形成偶数节点链表
            even.next = odd.next;
            even = even.next;
        }
        // 这里后面不能使用 head.next 代替 evenHead,因为原链表结构以及变化，head.next 指向已经变化；
        odd.next = evenHead;
        // 这里返回值为 head，不能是 odd，因为 odd 以及是奇链表的尾结点。
        return head;
    }
}
