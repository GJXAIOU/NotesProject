package com.gjxaiou.advanced.day06;

public class JosephusProblem {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一：暴力破解
    public static Node josephusKill1(Node head, int num) {
        if (head == null || head.next == head || num < 1) {
            return head;
        }
        Node last = head;
        // 首先转到一圈中的最后一个节点
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        // 遍历圈中每一个节点
        // 例如 1 2 3 4 5
        while (head != last) {
            // 如果下一个结点就是要删除的
            if (++count == num) {
                // 将要删除的结点链接到 last 后面，因为后面还要计数，所以如果删除则就不能实现每隔多少个删除了
                last.next = head.next;
                // count = 0 重新计数
                count = 0;
            } else {
                // 如果不是要删除的，last 往后移动一个，进入下一轮循环
                last = last.next;
            }
            // 每次都从 last.next 开始
            head = last.next;
        }
        return head;
    }

    // 方法二：递推
    public static Node josephusKill2(Node head, int num) {
        if (head == null || head.next == head || num < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1; // tmp -> list size
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, num); // tmp -> service node position
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static int getLive(int i, int num) {
        if (i == 1) {
            return 1;
        }
        // 计算出新编号对应的旧编号，将该旧编号作为下一次计算的新编号
        return (getLive(i - 1, num) + num - 1) % i + 1;
    }


    /////////////  测试程序   /////////////
    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = josephusKill1(head1, 3);
        printCircularList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill2(head2, 3);
        printCircularList(head2);

    }

}