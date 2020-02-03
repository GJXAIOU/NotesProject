package tree.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/1 14:17
 */
public class LeetCode104 {
    // 二叉树结构
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归实现二叉树最大深度
     * 时间复杂度O(n)
     * 空间复杂度:线性表最差O(n)、二叉树完全平衡最好O(logn)
     */
    private int maxDepth(TreeNode root) {
        // 递归退出条件，到叶子节点
        if (root == null) {
            return 0;
        }
        // 计算左子树最大深度
        int leftMaxDepth = maxDepth(root.left);
        // 计算右子树最大深度
        int rightMaxDepth = maxDepth(root.right);
        // 以某个节点为根节点的数的最大深度为 max
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
