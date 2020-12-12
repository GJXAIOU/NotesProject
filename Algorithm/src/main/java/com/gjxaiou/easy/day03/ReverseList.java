package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 翻转单向链表和双向链表
 */
public class ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 反转单向链表；图解：https://blog.csdn.net/xyh269/article/details/70238501
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    /**
     * 反转双向链表
     *
     * @param head
     * @return
     */
    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("Double Linked List: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printDoubleLinkedList(head2);
        printDoubleLinkedList(reverseList(head2));
    }
}

/**
 * 自测默写
 */
//class MyReverseList {
//    public static class Node {
//        int value;
//        Node next;
//
//        Node(int value) {
//            this.value = value;
//        }
//    }
//
//    public static Node reverseList(Node head) {
//        Node pre = null;
//        Node temp = null;
//        while (head != null) {
//            temp = head.next;
//            head.next = pre;
//            pre = head;
//            head = temp;
//        }
//        return pre;
//    }
//
//    public static class DoubleNode {
//        int value;
//        DoubleNode pre;
//        DoubleNode next;
//
//        DoubleNode(int value) {
//            this.value = value;
//        }
//
//        public static DoubleNode reverseDoubleNode(DoubleNode head) {
//            DoubleNode pre = null;
//            DoubleNode temp = null;
//            while (head != null) {
//                temp = head.next;
//                head.next = pre;
//                head.pre = temp;
//                pre = head;
//                head = temp;
//            }
//            return pre;
//        }
//    }
//}
