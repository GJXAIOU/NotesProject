package com.gjxaiou.easy.day04;

/**
 * 判断是否为平衡树
 *
 * @author GJXAIOU
 */
public class IsBalancedTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalance(Node head) {
        // 这里需要使用数组，因为作为参数传入 getHeight 函数，同时在该函数中修改了值，需要同步修改这里的值返回值才会变化。
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    public static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int leftHeight = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rightHeight = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        // 如果左右高度差大于 1 则返回 false
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res[0] = false;
        }
        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));
    }
}