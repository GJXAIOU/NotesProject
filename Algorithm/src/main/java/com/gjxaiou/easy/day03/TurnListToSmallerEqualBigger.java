package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 将链表分为小于、等于、大于三个部分
 */
public class TurnListToSmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    // 方案一：模拟荷兰国旗问题，空间复杂度为：O（N），不保证稳定性
    public static Node listPartition1(Node head, int num) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArr, num);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int num) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < num) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == num) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }


    /**
     * 方案二：实现稳定且空间复杂度为O(1)
     */
    public static Node listPartition2(Node head, int num) {
        // 定义三个部分的头结点和尾结点
        Node lessHead = null;
        Node lessTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node moreHead = null;
        Node moreTail = null;

        Node next = null;
        // 步骤一：遍历整个链表，将每次结点都分配到三个链表之一
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < num) {
                // 放在小的部分，小的部分如果头结点为空，则头结点和尾结点均指向 head。
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    // 小的部分结尾加上该结点，然后结尾指针指向该结点
                    lessTail.next = head;
                    lessTail = head;
                }
                // 如果是相等
            } else if (head.value == num) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
                // 如果是大于部分
            } else {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                } else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }

            head = next;
        }

        // 小于部分和等于部分相连接
        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }
        // 中间部分和大于部分相连接
        if (equalTail != null) {
            equalTail.next = moreHead;
        }
        // 最终返回结果为 lessHead/equalHead/moreHead（最近的一个不为空的）
        return lessHead != null ? lessHead : equalHead != null ? equalHead : moreHead;
    }


    // 测试程序
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }
}
