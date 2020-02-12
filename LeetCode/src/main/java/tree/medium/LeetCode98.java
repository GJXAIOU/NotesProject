package tree.medium;

import java.util.LinkedList;

/**
 * @Author GJXAIOU
 * @Date 2020/2/12 14:08
 */
public class LeetCode98 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：使用中序遍历判断是否为递增数列
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode cur2 = null;
        while (cur != null) {
            cur2 = cur.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur) {
                    cur2 = cur2.right;
                }

                if (cur2.right == null) {
                    cur2.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            // 中序遍历之后，如果不是递增数列，就不是搜索二叉树
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }

    // 方法二：递归
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        return helper(node.left, lower, val);
    }

    // 方法三：迭代
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST3(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) {
                continue;
            }
            val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }
}
