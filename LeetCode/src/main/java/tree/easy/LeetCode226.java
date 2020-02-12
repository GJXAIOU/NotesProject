package tree.easy;

import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/12 11:49
 */
public class LeetCode226 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：递归
    public TreeNode invertTree(TreeNode root) {
        // 即使特殊情况，也是递归的终止条件
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 方法二：深度优先搜索
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 使用一个队列存放左右孩子还没有交换的结点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 取出当前队列中节点，然后交换其左右结点
            TreeNode currentNode = queue.poll();
            TreeNode tempNode = currentNode.left;
            currentNode.left = currentNode.right;
            currentNode.right = tempNode;

            // 如果当前结点的左右结点不空，将其压入队列中
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return root;
    }
}
