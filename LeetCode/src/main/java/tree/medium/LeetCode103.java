package tree.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/2/18 15:54
 */
public class LeetCode103 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resList;
        }
        Queue<TreeNode> levelQueue = new LinkedList<TreeNode>();
        int level = 0;

        levelQueue.add(root);
        while (!levelQueue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            resList.add(levelList);

            int levelLength = levelQueue.size();
            for (int i = 0; i < levelLength; i++) {
                TreeNode remove = levelQueue.remove();
                // 偶数行将 queue 中元素加入 levelList 尾部，奇数行将元素加入 levelList 头部
                if (level % 2 == 0) {
                    resList.get(level).add(remove.val);
                } else {
                    // add(int index, E element)，在此列表中的指定位置插入指定元素
                    resList.get(level).add(0, remove.val);
                }
                if (remove.left != null) {
                    levelQueue.add(remove.left);
                }
                if (remove.right != null) {
                    levelQueue.add(remove.right);
                }
            }
            level++;
        }
        return resList;
    }
}
