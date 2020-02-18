package tree.easy;

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
}
