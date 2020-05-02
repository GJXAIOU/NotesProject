package tree.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 13:39
 */
public class Offer55 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if (root.left != null) {
            leftHeight = maxDepth(root.left);
        }
        if (root.right != null) {
            rightHeight = maxDepth(root.right);
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
