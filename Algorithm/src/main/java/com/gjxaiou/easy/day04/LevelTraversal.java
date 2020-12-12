package com.gjxaiou.easy.day04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author GJXAIOU
 * @Date 2020/11/21 21:10
 */
public class LevelTraversal {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> resList = new ArrayList<>();
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);

        while (!levelQueue.isEmpty()) {
            // 每一层都使用一个 List<Integer> 保存值，同样每层的值都加入 Queue 中
            List<Integer> levelList = new ArrayList<>();
            // 该层 list 长度就是等于 queue 长度，queue 长度也是该层结点的个数
            int levelLength = levelQueue.size();
            for (int i = 0; i < levelLength; i++) {
                // 弹出队列中元素，加到对应 level 链表后面
                TreeNode remove = levelQueue.remove();
                levelList.add(remove.value);

                // 将下一层的左右孩子加入队列，当前层遍历结束之后，相当于下一层的左右孩子都加入了，就可以遍历下一层了。
                if (remove.left != null) {
                    levelQueue.add(remove.left);
                }
                if (remove.right != null) {
                    levelQueue.add(remove.right);
                }
            }
            // 下一层元素都已经加入到 levelQueue 中，开始遍历输出下一层元素
            resList.add(levelList);
        }
        return resList;
    }
}