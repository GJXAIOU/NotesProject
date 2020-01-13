package nowcoder.easy.day04;

/**
 * 最直观的打印二叉树
 *
 * @author GJXAIOU
 * 在节点值两边加上特定的字符串标记来区分孩子和位置以及之间的位置关系：
 * HXH：表示头结点 X；vYv：表示节点 Y 是左下方最近节点的孩子；^Z^：表示节点 Z 是左上方最近节点的孩子
 * 遍历树的顺序为：右子树 -> 根 -> 左子树；
 * 避免节点值长度不同影响对其，规定每个节点值长度为固定值（这里规定为 10）
 */
public class PrintBinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 10);
        System.out.println();
    }

    /**
     * @param head               ：传入的节点
     * @param treeHeight：层数（根节点为 0）
     * @param to                 ：表示的特定节点  H表示根节点   ^表示父亲节点在左上方　v表示父亲节点在左下方
     * @param totalLength        ：指定每一个节点打印的宽度(总宽度)
     */
    public static void printInOrder(Node head, int treeHeight, String to, int totalLength) {
        if (head == null) {
            return;
        }
        // 递归遍历右子树
        printInOrder(head.right, treeHeight + 1, "v", totalLength);
        // 在节点值两边加上标识符
        String val = to + head.value + to;
        int lenValue = val.length();
        // 节点值左边的空格数：（总长度 - 节点值长度）/ 2
        int lenL = (totalLength - lenValue) / 2;
        // 节点值右边的空格数
        int lenR = totalLength - lenValue - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        // treeHeight * totalLength 为打印的节点前空格长度
        System.out.println(getSpace(treeHeight * totalLength) + val);
        // 递归遍历左子树
        printInOrder(head.left, treeHeight + 1, "^", totalLength);
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
        Node head = new Node(1);
        head.left = new Node(-22);
        head.right = new Node(3);
        head.left.left = new Node(2);
        head.right.left = new Node(55);
        head.right.right = new Node(66);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);
    }
}
