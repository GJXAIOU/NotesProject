package com.gjxaiou.advanced.day04;

public class BiggestSubBSTInTree {

    // 树节点的结构
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 对于递归函数返回的结构类型
    public static class ReturnType {
        // 左右搜索二叉子树节点数的最大值
        public int size;
        // 左右搜索二叉子树节点数较大的子树的头结点
        public Node head;
        // 右树的最小值
        public int min;
        // 左树的最大值
        public int max;

        public ReturnType(int size, Node head, int min, int max) {
            this.size = size;
            this.head = head;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * 方式一：使用后续遍
     */
    public static Node biggestSubBST1(Node head) {
        return process(head).head;
    }

    // 采用后序遍历的方式进行递归，因为需要左右的信息来构造头部的信息
    public static ReturnType process(Node head) {
        if (head == null) {
            // 返回最大值，比较谁小的时候不会影响比较结果
            return new ReturnType(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // 分别将左右孩子传入之后返回左树和右树的信息
        Node left = head.left;
        ReturnType leftSubTressInfo = process(left);

        Node right = head.right;
        ReturnType rightSubTressInfo = process(right);

        // 可能性 3
        int includeItSelf = 0;
        // 如果左树上最大搜索二叉子树的头部是该节点的左孩子，右树上最大搜索二叉子树的头部是该节点的右孩子，同时左树上最大值值小于该结点，右树上值大于该节点
        if (leftSubTressInfo.head == left
                && rightSubTressInfo.head == right
                && head.value > leftSubTressInfo.max
                && head.value < rightSubTressInfo.min
        ) {
            includeItSelf = leftSubTressInfo.size + 1 + rightSubTressInfo.size;
        }
        // 可能性 1
        int p1 = leftSubTressInfo.size;
        // 可能性 2
        int p2 = rightSubTressInfo.size;
        // 该节点返回的最大值是三种可能性中最大值
        int maxSize = Math.max(Math.max(p1, p2), includeItSelf);
        // 返回值头部 P1 大说明来自左子树最大搜索二叉树头部，P2 大则说明......
        Node maxHead = p1 > p2 ? leftSubTressInfo.head : rightSubTressInfo.head;
        if (maxSize == includeItSelf) {
            maxHead = head;
        }

        return new ReturnType(maxSize,
                maxHead,
                Math.min(Math.min(leftSubTressInfo.min, rightSubTressInfo.min), head.value),
                Math.max(Math.max(leftSubTressInfo.max, rightSubTressInfo.max), head.value));
    }


    /*
     优化方式：将三个值以数组形式存放，
     */
    public static Node biggestSubBST(Node head) {
        // 0->size, 1->min, 2->max
        int[] record = new int[3];
        return posOrder(head, record);
    }

    public static Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }


    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
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

    public static void main(String[] args) {

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printTree(head);
        Node bst = biggestSubBST(head);
        Node node = biggestSubBST1(head);
        printTree(bst);
        System.out.println("=====================================");
        printTree(node);

    }

}
