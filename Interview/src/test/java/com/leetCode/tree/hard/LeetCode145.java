package tree.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/2/17 11:36
 */
public class LeetCode145 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归解法：左右中顺序
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> resList = new ArrayList<>();
        posOrder(root, resList);
        return resList;
    }

    public void posOrder(TreeNode root, List<Integer> resList) {
        if (root != null) {
            if (root.left != null) {
                posOrder(root.left, resList);
            }
            if ((root.right != null)) {
                posOrder(root.right, resList);
            }
            resList.add(root.val);
        }
    }

    // 非递归解法：使用两个栈
    // 后序遍历顺序为：左右中，先序遍历顺序为：中左右，修改为：中、右、左，然后遍历结果放入栈中，最后弹出栈中元素即为左右中
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> resList = new ArrayList<>();
        Stack<TreeNode> tempStack = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        tempStack.push(root);
        while (!tempStack.isEmpty()) {
            TreeNode pop = tempStack.pop();
            resStack.push(pop);
            if (pop.left != null) {
                tempStack.push(pop.left);
            }
            if (pop.right != null) {
                tempStack.push(pop.right);
            }
        }
        while (!resStack.isEmpty()) {
            resList.add(resStack.pop().val);
        }
        return resList;
    }

    // 非递归解法：使用一个栈
    public List<Integer> postorderTraversal3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> resList = new ArrayList<>();
        Stack<TreeNode> resStack = new Stack<>();
        resStack.push(root);
        while (!resStack.isEmpty()) {
            TreeNode stackTopNode = resStack.peek();
            if (stackTopNode.left != null && stackTopNode.left != root && stackTopNode.right != root) {
                resStack.push(stackTopNode.left);
            } else if (stackTopNode.right != null && stackTopNode.right != root) {
                resStack.push(stackTopNode.right);
            } else {
                resList.add(resStack.pop().val);
                root = stackTopNode;
            }
        }
        return resList;
    }
}
