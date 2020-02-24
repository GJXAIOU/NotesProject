package list.easy;

import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 14:52
 */

public class Offer06 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        int count = 0;
        // 首先反转链表
        while (cur != null) {
            count++;
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        int[] res = new int[count];
        // 打印反转之后的链表
        int i = 0;
        while (pre != null) {
            res[i++] = pre.val;
            pre = pre.next;
        }
        return res;
    }

    // 方法二：不反转链表，反向输入数组
    public int[] reversePrint2(ListNode head) {
        ListNode node = head;
        int count = 0;
        // 求得链表长度
        while (node != null) {
            node = node.next;
            count++;
        }
        int[] arr = new int[count];
        // 从数组最后一位开始填充
        node = head;
        for (int i = count - 1; i >= 0; i--) {
            arr[i] = node.val;
            node = node.next;
        }
        return arr;
    }

    // 方法三：使用栈
    public int[] reversePrint3(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        // 首先将所有链表中元素入栈
        while (head != null) {
            stack.push(head.val);
            head = head.next;
            count++;
        }

        // 所有的元素打印输出
        int[] res = new int[count];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }


    public static void main(String[] args) {

        Offer06 offer06 = new Offer06();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        int[] ints = offer06.reversePrint(listNode);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
