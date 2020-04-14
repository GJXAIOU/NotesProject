package tree.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/14 18:11
 */
public class LeetCode257 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> resString = new ArrayList<>();
        // 初始路径为 ""，每一步的结果，resPath 为最终结果
        dfs(root, "", resString);

        return resString;
    }

    public void dfs(TreeNode node, String path, List<String> resString) {
        // 终止条件
        if (node != null) {
            // 首先加上自身
            path += String.valueOf(node.val);
            // 如果该结点是叶子结点，path 结束加入结果中
            if (node.left == null && node.right == null) {
                resString.add(path);
            } else {
                path += "->";
                dfs(node.left, path, resString);
                dfs(node.right, path, resString);

            }
        }
    }

    // 方法二：迭代

    public List<String> binaryTreePaths2(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        if (root == null)
            return paths;

        LinkedList<TreeNode> node_stack = new LinkedList();
        LinkedList<String> path_stack = new LinkedList();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while (!node_stack.isEmpty()) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null))
                paths.add(path);
            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + Integer.toString(node.left.val));
            }
            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + Integer.toString(node.right.val));
            }
        }
        return paths;
    }


}
