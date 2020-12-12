package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 打印两个有序列表公共部分
 */
public class PrintCommonPart {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 打印两个有序链表都有的元素
    public static void printCommonPart(Node head1, Node head2) {
        System.out.print("Common Part: ");
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    // 输出输入的两个链表元素
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        printLinkedList(node2);
        printCommonPart(node1, node2);
    }
}

//class MyCommonPart {
//    static class Node {
//        int value;
//        Node next;
//
//        Node(int value) {
//            this.value = value;
//        }
//    }
//
//    public static void printCommonPart(Node head1, Node head2) {
//        while (head1 != null && head2 != null) {
//            if (head1.value == head2.value) {
//                System.out.println(head1.value + " ");
//                head1 = head1.next;
//                head2 = head2.next;
//            } else if (head1.value < head2.value) {
//                head1 = head1.next;
//            } else if (head1.value > head2.value) {
//                head2 = head2.next;
//            }
//        }
//    }
//}
