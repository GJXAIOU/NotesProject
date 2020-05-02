package list.easy;

import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/30 18:18
 */
public class LeetCode234 {
    // 单链表结构
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 方法一：使用栈
    public boolean isPalindrome(ListNode head) {
        // 这里测试用例中 [] 的结果为 true
//        if (head == null) {
//            return false;
//        }
        Stack<ListNode> stack = new Stack<>();
        // 遍历 ListNode，将所有节点值放入栈中
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 依次弹出栈中值和 ListNode 比较
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 方法二：只将链表后半部分进行反转
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode cur = head;
        ListNode right = head.next;
        // 遍历一遍，right 正好在中间
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        // 将 right 右边结点压入栈中
        Stack<ListNode> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        // 遍历比较
        while (!stack.isEmpty()) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    // 方法三：空间复杂度O（1）
    public static boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // slowPointer 是慢指针，fastPointer 是快指针
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        // 寻找中间节点
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // 快指针指向右半部分第一个节点
        fastPointer = slowPointer.next;
        // 中间节点指向 null
        slowPointer.next = null;
        // tmpNode 保存下一个结点
        ListNode tmpNode = null;
        // 反转右半部分
        while (fastPointer != null) {
            tmpNode = fastPointer.next;
            fastPointer.next = slowPointer;
            slowPointer = fastPointer;
            fastPointer = tmpNode;
        }
        // 因为最后要逆序回来，所有要将最后的节点保留
        tmpNode = slowPointer;
        fastPointer = head;
        boolean res = true;
        // 从两头往中间走，逐个遍历比较
        while (slowPointer != null && fastPointer != null) {
            if (slowPointer.val != fastPointer.val) {
                res = false;
                break;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }
        slowPointer = tmpNode.next;
        tmpNode.next = null;
        // 把逆序的再逆回来
        while (slowPointer != null) {
            fastPointer = slowPointer.next;
            slowPointer.next = tmpNode;
            tmpNode = slowPointer;
            slowPointer = fastPointer;
        }
        return res;
    }
}
