package tree.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 16:42
 */
public class Offer54 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法一：中序排列然后取倒数第 K 值，或者逆中序排列（右中左）然后取第 K 值。
    public int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        List<Integer> resList = new ArrayList<>();
        inOder(root, resList);
        return Integer.valueOf(resList.get(resList.size() - k));
    }

    public void inOder(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        inOder(root.left, resList);
        resList.add(root.val);
        inOder(root.right, resList);
    }

    // 方法二：计数不记值
    int count;
    int result = -1;

    public int kthLargest2(TreeNode root, int k) {
        count = k;
        kthLargest(root);
        return result;
    }

    public void kthLargest(TreeNode root) {
        if (root != null) {
            kthLargest(root.right);
            if (count == 1) {
                result = root.val;
                count--;
                return;
            }
            count--;
            kthLargest(root.left);
        }
    }
}

