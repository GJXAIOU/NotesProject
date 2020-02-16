package com.gjxaiou.easy.day04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列号和反序列化二叉树
 *
 * @author GJXAIOU
 */
public class SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用递归版本的先序遍历实现二叉树的序列化
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        // 形成字符串格式为：结点值!
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 对先序遍历序列化得到的字符串进行反序列化
    public static Node reconByPreString(String preStr) {
        // 首先将字符串分割
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i != values.length; i++) {
            // add() 和 offer() 都是向队列中添加一个元素，如果想在一个满的队列中加入一个新项，调用 add() 方法就会抛出一个 unchecked 异常，而调用
            //offer() 方法会返回 false
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    // 通过队列建出树
    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


    // 按层遍历二叉树的序列号
    public static String serialByLevel(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            if (head.right != null) {
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    // 按层遍历二叉树的反序列化
    public static Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    // 打印二叉树树形结构--------
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 10);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
    //-----------------------------

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("通过先序遍历序列化二叉树: " + pre);
        head = reconByPreString(pre);
        System.out.print("通过先序遍历反序列二叉树, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("通过层序遍历序列化二叉树: " + level);
        head = reconByLevelString(level);
        System.out.print("通过层序遍历反序列化二叉树, ");
        printTree(head);
        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("通过先序遍历序列化二叉树: " + pre);
        head = reconByPreString(pre);
        System.out.print("通过先序遍历反序列二叉树, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("通过层序遍历序列化二叉树: " + level);
        head = reconByLevelString(level);
        System.out.print("通过层序遍历反序列化二叉树, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("通过先序遍历序列化二叉树: " + pre);
        head = reconByPreString(pre);
        System.out.print("通过先序遍历反序列二叉树, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("通过层序遍历序列化二叉树: " + level);
        head = reconByLevelString(level);
        System.out.print("通过层序遍历反序列化二叉树, ");
        printTree(head);

        System.out.println("====================================");
    }
}
