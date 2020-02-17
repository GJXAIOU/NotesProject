package tree.medium;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/2/17 10:49
 */
public class LeetCode144 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：递归方式
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        preOrder(root, resList);
        return resList;
    }

    public void preOrder(TreeNode root, List resList) {
        if (root != null) {
            resList.add(root.val);
            if (root.left != null) {
                preOrder(root.left, resList);
            }
            if (root.right != null) {
                preOrder(root.right, resList);
            }
        }
    }

    // 方法二：非递归方式
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 先序遍历是：中、左、右，所以使用一个栈，
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resList = new ArrayList<>();
        // 首先加入头结点
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            resList.add(pop.val);

            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return resList;
    }
}

