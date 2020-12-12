package com.gjxaiou.hand;

import com.gjxaiou.easy.day08.FaceBook;

/**
 * @Author GJXAIOU
 * @Date 2020/2/16 17:03
 */
public class EasyDay04IsBalancedTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 1, res);
        return res[0];
    }

    public int getHeight(TreeNode root, int level, boolean[] res) {
        if (root == null) {
            return level;
        }
        int leftHeight = getHeight(root.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rightLength = getHeight(root.right, level + 1, res);
        if (!res[0]) {
            return level;
        }

        if (Math.abs(leftHeight - rightLength) > 1) {
            res[0] = false;
        }

        return Math.max(leftHeight, rightLength);
    }
}
