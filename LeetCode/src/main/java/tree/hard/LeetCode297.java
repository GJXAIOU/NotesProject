package tree.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/17 15:23
 */
public class LeetCode297 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 先序遍历序列化
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        String res = "";
        res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // 先序遍历实现反序列化
    public TreeNode deserialize(String data) {
        String[] dataString = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < dataString.length; i++) {
            queue.offer(dataString[i]);
        }
        return reconPreOrder(queue);
    }

    public TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = reconPreOrder(queue);
        node.right = reconPreOrder(queue);
        return node;
    }
}
