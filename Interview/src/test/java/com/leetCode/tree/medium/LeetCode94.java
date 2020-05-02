package tree.medium;

import list.medium.LeetCode19;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/31 20:57
 */
public class LeetCode94 {
    // 树结构
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：递归方式
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        inOrderRecur(root, resList);
        return resList;
    }

    public static void inOrderRecur(TreeNode head, List<Integer> resList) {
        if (head != null) {
            if (head.left != null) {
                inOrderRecur(head.left, resList);
            }

            resList.add(head.val);

            if (head.right != null) {
                inOrderRecur(head.right, resList);
            }
        }
    }


    // 非递归版本
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    resList.add(root.val);
                    root = root.right;
                }
            }
        }
        return resList;
    }

}
