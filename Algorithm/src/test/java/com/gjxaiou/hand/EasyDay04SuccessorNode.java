package com.gjxaiou.hand;

import com.sun.istack.internal.localization.NullLocalizable;

/**
 * @Author GJXAIOU
 * @Date 2020/2/16 14:22
 */
public class EasyDay04SuccessorNode {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode getSuccessorNode(TreeNode root) {
        if (root == null) {
            return root;
        }
        while (root.right != null) {
            getMostLeft(root.right);
        }
        // 当右结点为空
        TreeNode node = root.parent;
        while (node != null) {
            if (root != node.left) {
                root = node;
                node = root.parent;
            }
        }
        return node;
    }

    public TreeNode getMostLeft(TreeNode root) {
        if (root == null) {
            return root;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
