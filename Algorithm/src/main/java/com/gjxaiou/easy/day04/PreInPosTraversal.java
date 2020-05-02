package com.gjxaiou.easy.day04;

import java.util.Stack;

/**
 * 递归版和非递归版本的先序、中序、后序遍历
 * @author GJXAIOU
 */
public class PreInPosTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 递归版本实现先序、中序、后序遍历，唯一变化就是 print() 函数位置不同。
     */
    // 先序遍历：中（当前节点）、左、右
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 中序遍历：左、中（当前结点）、右
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // 后序遍历：左、右、中（当前结点）
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }


    /**
     * 非递归版本实现先序、中序、后序遍历
     */
    // 非递归版本实现先序遍历
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            // 准备一个栈
            Stack<Node> stack = new Stack<Node>();
            // 首先放入头结点
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 非递归方式实现中序遍历
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                // 首先压入头结点然后一直压入所有的左孩子
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                    // 弹出栈顶然后一直弹出右孩子
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 后序遍历方式一：先使用中左右的顺序将元素压入栈中，然后遍历栈弹出即可
    public static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack1 = new Stack<Node>();
            Stack<Node> stack2 = new Stack<Node>();
            stack1.push(head);
            // 这里和前序遍历类似，只不过左右节点压入顺序相反
            while (!stack1.isEmpty()) {
                head = stack1.pop();
                // 将打印语句替换为压栈语句
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.left);
                }
                if (head.right != null) {
                    stack1.push(head.right);
                }
            }

            // 逐个弹出栈 stack2 中元素即为左、右、中顺序
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }

    // 后续遍历方式二：使用一个栈
    // head 表示最近被打印的结点
    public static void posOrderUnRecur2(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node stackTopNode = null;
            while (!stack.isEmpty()) {
                // stackTopNode 表示栈顶结点
                stackTopNode = stack.peek();
                // 如果栈顶结点的左右结点和最近打印的结点都不相等：说明栈顶结点的左右孩子都不是最近打印的结点，同样由于左右孩子分别为左右子树的头结点，
                // 根据后序遍历的特点（左右中），则左右子树都没有被打印过，所以压入左子树。
                if (stackTopNode.left != null && head != stackTopNode.left && head != stackTopNode.right) {
                    stack.push(stackTopNode.left);
                    // 如果上面没有执行：左子树不存在或者左子树刚刚打印过或者右子树刚刚打印过，如果是前两种情况，就接着打印右子树
                } else if (stackTopNode.right != null && head != stackTopNode.right) {
                    stack.push(stackTopNode.right);
                    // 上面还没有执行说明左右子树都空或者都打印完了，弹出该结点打印，然后更新。
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = stackTopNode;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // 递归版本
        System.out.println("==============递归版本==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // 非递归版本
        System.out.println("============非递归版本=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
        posOrderUnRecur2(head);
    }
}
