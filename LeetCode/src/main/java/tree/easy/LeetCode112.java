package tree.easy;

import java.util.LinkedList;

/**
 * @Author GJXAIOU
 * @Date 2020/2/19 10:26
 */
public class LeetCode112 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 方法一：深度优先搜索：递归形式
    // 无后效性问题：当前值和剩余值
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);

    }

    public boolean helper(TreeNode root, int curSum, int sum) {
        if (root == null) {
            return false;
        }
        curSum += root.val;
        if (root.left == null && root.right == null) {
            return curSum == sum;
        } else {
            return helper(root.left, curSum, sum) || helper(root.right, curSum, sum);
        }
    }

    // 方法二：在叶子节点判断当前路径上结点和
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int curSum = 0;
        curSum += root.val;
        // 在叶子节点判断该路径上和是否为 sum
        if (root.left == null && root.right == null) {
            return curSum == sum;
        }
        return (hasPathSum(root.left, sum - curSum) || hasPathSum(root.right, sum - curSum));
    }

    // 方法三：迭代方式
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root != null) {
            LinkedList<TreeNode> nodeStack = new LinkedList();
            LinkedList<Integer> sumStack = new LinkedList();
            nodeStack.add(root);
            sumStack.add(sum - root.val);

            TreeNode node;
            int curSum;
            while (!nodeStack.isEmpty()) {
                node = nodeStack.pollLast();
                curSum = sumStack.pollLast();
                if ((node.right == null) && (node.left == null) && (curSum == 0)) {
                    return true;
                }

                if (node.right != null) {
                    nodeStack.add(node.right);
                    sumStack.add(curSum - node.right.val);
                }
                if (node.left != null) {
                    nodeStack.add(node.left);
                    sumStack.add(curSum - node.left.val);
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
