package tree.medium;

/**
 * @author GJXAIOU
 * @create 2020/05/04 17:25
 */
public class LeetCode236 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode res;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    public boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        // 递归结束条件: 如果当前节点为空,或已经查找到结果,则直接返回false
        if (node == null || this.res != null) {
            return false;
        }
        // 递归查找左右子树是否包含目标节点
        boolean left = dfs(node.left, p, q);
        boolean right = dfs(node.right, p, q);
        // 判断当前节点是否为目标节点
        boolean mid = (node == p || node == q);
        // 判断如果左右子树分别包含其中一个目标节点,或当前节点是目标节点且左右子树包含另一目标节点,则当前节点目标节点的就是最近公共祖先
        if ((left && right) || (mid && (left || right))) {
            res = node;
        }
        // 返回当前节点或其子树中是否包含目标节点
        return left || right || mid;
    }
}
