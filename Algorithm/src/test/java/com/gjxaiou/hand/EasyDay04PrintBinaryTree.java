package com.gjxaiou.hand;

/**
 * @Author GJXAIOU
 * @Date 2020/2/16 11:03
 */
public class EasyDay04PrintBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        print(root, 0, "H", 10);
    }

    public void print(TreeNode root, int level, String sign, int totalLength) {
        print(root.right, level + 1, "V", totalLength);

        String value = sign + root.val + sign;
        int valueLength = value.length();
        int leftLength = (totalLength - valueLength) >>> 1;
        int rightLength = totalLength - valueLength - leftLength;
        value = space(leftLength) + value + space(rightLength);
        System.out.println(space(level * totalLength) + value);

        print(root.left, level + 1, "^", totalLength);

    }

    public String space(int num) {
        String space = " ";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            stringBuffer.append(space);
        }
        return stringBuffer.toString();
    }
}
