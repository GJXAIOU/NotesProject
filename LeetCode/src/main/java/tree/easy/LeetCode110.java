package tree.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/12 15:58
 */
public class LeetCode110 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：自顶向下的递归
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(height(root.left) - height(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        // 如果空树则高度为 -1
        if (root == null) {
            return -1;
        }
        // 以该结点为根的树的高度等于该结点 + 左右树中最高的那个
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // 方法二：自底向上的递归
// Utility class to store information from recursive calls
    final class TreeInfo {
        public final int height;
        public final boolean balanced;

        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    class Solution {
        // Return whether or not the tree at root is balanced while also storing
        // the tree's height in a reference variable.
        private TreeInfo isBalancedTreeHelper(TreeNode root) {
            // An empty tree is balanced and has height = -1
            if (root == null) {
                return new TreeInfo(-1, true);
            }

            // Check subtrees to see if they are balanced.
            TreeInfo left = isBalancedTreeHelper(root.left);
            if (!left.balanced) {
                return new TreeInfo(-1, false);
            }
            TreeInfo right = isBalancedTreeHelper(root.right);
            if (!right.balanced) {
                return new TreeInfo(-1, false);
            }

            // Use the height obtained from the recursive calls to
            // determine if the current node is also balanced.
            if (Math.abs(left.height - right.height) < 2) {
                return new TreeInfo(Math.max(left.height, right.height) + 1, true);
            }
            return new TreeInfo(-1, false);
        }

        public boolean isBalanced(TreeNode root) {
            return isBalancedTreeHelper(root).balanced;
        }
    }

}