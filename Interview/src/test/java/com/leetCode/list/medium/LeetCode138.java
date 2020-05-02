package list.medium;


import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/1/31 13:02
 */
public class LeetCode138 {
    // 链表结构
    class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 方法一：使用 map 结构
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        // 将节点和对应的拷贝节点放入 Map 中；
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 遍历 map 中所有 value 节点，为其赋值：next 和 random
        cur = head;
        while (cur != null) {
            // 相当于 复制节点.next = 原来对应结点的.next
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 方法二：
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 复制新的结点链接到对应结点后面
        Node cur = head;
        Node nextNode = null;
        while (cur != null) {
            nextNode = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = nextNode;
            cur = nextNode;
        }

        // 两个结点一起，根据源节点对其对应的复制结点赋值
        cur = head;
        Node copyNode = null;
        while (cur != null) {
            nextNode = cur.next.next;
            copyNode = cur.next;
            // 复制结点的 random = 原来结点的 random 的下一节点（该结点为原来结点的 random 结点的复制结点）
            copyNode.random = cur.random != null ? cur.random.next : null;
            cur = nextNode;
        }

        // 最后将两个链表分开来
        Node res = head.next;
        cur = head;
        while (cur != null) {
            nextNode = cur.next.next;
            copyNode = cur.next;
            cur.next = nextNode;
            copyNode.next = nextNode != null ? nextNode.next : null;
            cur = nextNode;
        }
        return res;
    }
}
