package tree.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/12 15:07
 */
public class LeetCode101 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode node1, TreeNode node2) {
        // 如果两个都为空，则返回 true
        if (node1 == null && node2 == null) {
            return true;
        }
        // 如果两者中只有一个为空，则不相等（上面要先判断，这里后判断）
        if (node1 == null || node2 == null) {
            return false;
        }
        // 两个都有值并且相等才返回 true
        return (node1.val == node2.val) && isMirror(node1.left, node2.right) && isMirror(node1.right,
                node2.left);
    }

    // 方法二：迭代
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}
