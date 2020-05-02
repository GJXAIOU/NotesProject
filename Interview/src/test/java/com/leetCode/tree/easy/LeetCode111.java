package tree.easy;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/18 17:25
 */
public class LeetCode111 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：层次遍历
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 1;
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);
        while (!levelQueue.isEmpty()) {
            int length = levelQueue.size();
            for (int i = 0; i < length; i++) {
                TreeNode remove = levelQueue.remove();
                if (remove.left != null) {
                    levelQueue.add(remove.left);
                }
                if (remove.right != null) {
                    levelQueue.add(remove.right);
                }
                // 如果一个节点左右子节点都为空则此层结束
                if (remove.left == null && remove.right == null) {
                    return level;
                }
            }
            level++;
        }
        return level;
    }

    // 方法二：深度优先搜索：递归方式
    public int minDepth2(TreeNode root) {
        // baseCase
        if (root == null) {
            return 0;
        }
        //这道题递归条件里分为三种情况
        // 1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        // 2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度(因为只要右孩子就有高度)
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        // 这里其中一个节点为空，说明 leftDepth 和 rightDepth 有一个必然为 0，所以可以返回 leftDepth + rightDepth + 1;
        if (root.left == null || root.right == null) {
            return leftDepth + rightDepth + 1;
        }

        // 3.最后一种情况，也就是左右孩子都不为空，返回最小深度 + 1 即可
        return Math.min(leftDepth, rightDepth) + 1;
    }

    // 方法二：深度优先搜索：迭代方式
    public int minDepth3(TreeNode root) {
        // Pair<key value>，表示一对值， List<pair<>> 近似可以理解为 map<>
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        } else {
            // 根节点的高度设置为 1
            stack.add(new Pair(root, 1));
        }

        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            // 弹出栈顶元素
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int currentDepth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                minDepth = Math.min(minDepth, currentDepth);
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, currentDepth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, currentDepth + 1));
            }
        }
        return minDepth;
    }
}
