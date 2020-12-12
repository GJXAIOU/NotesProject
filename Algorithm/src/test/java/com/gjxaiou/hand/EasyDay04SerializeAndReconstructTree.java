package com.gjxaiou.hand;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/16 15:05
 */
public class EasyDay04SerializeAndReconstructTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serializeTree(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String value = root.val + "_";
        value += serializeTree(root.left);
        value += serializeTree(root.right);
        return value;
    }

    // 反序列化
    public TreeNode reconstructTree(String value) {
        // 首先字符串划分为数组（取出结点值）
        String[] val = value.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < val.length; i++) {
            queue.offer(val[i]);
        }
        return reconstruct(queue);
    }

    public TreeNode reconstruct(Queue<String> queue) {
        if (queue.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(queue.poll()));
        root.left = reconstruct(queue);
        root.right = reconstruct(queue);
        return root;
    }

}
