package com.gjxaiou.hand;

import com.sun.prism.ReadbackRenderTarget;
import com.sun.xml.internal.ws.encoding.RootOnlyCodec;

import java.util.Stack;

/**
 * 二叉树先序、中序、后续遍历方式
 *
 * @Author GJXAIOU
 * @Date 2020/2/14 21:53
 */
public class EasyDay04TreeTraversal {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归的方式实现
    // 先序遍历：先当前结点，然后左结点，然后右结点
    public void preOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    // 中序遍历：左边、中间、右边
    public void inOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecur(root.left);
        System.out.println(root + " ");
        inOrderRecur(root.right);
    }

    // 后续遍历：左边、右边、中间
    public void posOrderRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        System.out.println(root + " ");
    }

    // 使用非递归方式
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty()){
            stack.pop();
            if(root.right != null){
                stack.push(root.right);
            }
            if (root.left != null){
                stack.push(root.left);
            }
        }

    }
}
