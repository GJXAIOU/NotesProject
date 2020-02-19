package tree.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/19 17:10
 */
public class LeetCode662 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：层序遍历，同时使用 list 记录每一层所有元素的下标，然后将 list 中最后一个 - 最前面一个就是该层宽度
    // 树中结点下标：根节点为 1，左子树为： 2 * i，右子树为：2 * i + 1
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> levelQueue = new LinkedList<>();
        LinkedList<Integer> levelLocal = new LinkedList<>();
        levelQueue.add(root);
        levelLocal.add(1);
        // 结果至少为 1
        int res = 1;
        while (!levelQueue.isEmpty()) {
            // levelQueue 的大小就是当前层所有结点数目
            int count = levelQueue.size();
            // 遍历当前层所以结点，然后将他们的左右孩子结点加入
            for (int i = 0; i < count; i++) {
                TreeNode curNode = levelQueue.poll();
                Integer curIndex = levelLocal.removeFirst();
                if (curNode.left != null) {
                    levelQueue.offer(curNode.left);
                    levelLocal.add(curIndex * 2);
                }
                if (curNode.right != null) {
                    levelQueue.offer(curNode.right);
                    levelLocal.add(curIndex * 2 + 1);
                }
            }
            // 如果队列中只有一个元素（这行只有一个元素），则无需判断
            if (levelLocal.size() >= 2) {
                res = Math.max(res, levelLocal.getLast() - levelLocal.getFirst() + 1);
            }
        }
        return res;
    }

    // 方法二：深度优先搜索
    int ans;
    Map<Integer, Integer> left;

    public int widthOfBinaryTree2(TreeNode root) {
        ans = 0;
        left = new HashMap();
        dfs(root, 0, 0);
        return ans;
    }

    public void dfs(TreeNode root, int depth, int pos) {
        if (root == null) {
            return;
        }
        left.computeIfAbsent(depth, x -> pos);
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }
}
