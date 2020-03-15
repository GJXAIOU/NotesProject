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

        while (!stack.isEmpty()) {
            stack.pop();
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }

    // 中序遍历：先压入头结点，然后一直压左边，全部压完之后弹出，谈抽压入右结点
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                System.out.println(stack.pop().val + " ");
                root = root.right;
            }
        }
    }

    // 后序遍历：一个栈存入顺序为：中、右，左，一次弹出的元素存入另一个栈，然后将另一个栈中元素依次弹出即可
    public void posOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            if (root.right != null){
                stack1.push(root.right);
            }
            if (root.left != null){
                stack1.push(root.left);
            }
        }

        while (!stack2.isEmpty()){
            System.out.println(stack2.pop().val + " ");
        }
    }
}
