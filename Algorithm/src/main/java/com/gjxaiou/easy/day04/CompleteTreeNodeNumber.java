package com.gjxaiou.easy.day04;

/**
 * 计算完全二叉树的结点个数
 *
 * @author GJXAIOU
 */
public class CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     * @param node：表示当前节点
     * @param level：当前节点在第几层
     * @param treeHeight：整棵树的高度，为定值，左子树的最左边结点所在层即为树的高度
     * @return ：以这个结点为头的子树一共有多少个节点
     */
    public static int bs(Node node, int level, int treeHeight) {
        // 等于树的高度相当于最后一层，以该结点为头结点的树只有自身，所以结点数为 1
        if (level == treeHeight) {
            return 1;
        }
        // 如果 node 的右子树上的左边界层数到 treeHeight 层，即左子树是满二叉树，右子树为完全二叉树
        if (mostLeftLevel(node.right, level + 1) == treeHeight) {
            // 1 << (treeHeight - level)表示当前节点的左子树和当前节点的节点个数和，2^(treeHeight - level)个
            // 因为右孩子也是一个完全二叉树，使用递归求其总节点，就是后面部分；
            return (1 << (treeHeight - level)) + bs(node.right, level + 1, treeHeight);
            // 左子树是完全二叉树，右子树为满二叉树
        } else {
            // 没有到 treeHeight 层，则右树的高度比左树少一个，1 << (treeHeight - level - 1))
            //就是右树所有节点加上当前节点个数，然后后面是左树也是完全二叉树，递归求解；
            return (1 << (treeHeight - level - 1)) + bs(node.left, level + 1, treeHeight);
        }
    }

    // 获取以该结点为根的最左边结点所在的层数，就是当前树的高度
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
