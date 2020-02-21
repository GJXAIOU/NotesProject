package com.gjxaiou.easy.day08;

import java.util.Stack;

public class Code_06_ReverseStackUsingRecursive {
    /**
     * 每层递归取出栈底的元素并缓存到变量中，直到栈空；
     * 然后逆向将每层变量压入栈，最后实现原栈数据的逆序。
     *
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 依次返回1、2、3
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        // 依次压入3、2、1
        stack.push(i);
    }

    // 返回并且移除栈底元素（栈内元素<栈底>1、2、3<栈顶>变为2、3<栈顶>）.
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            // 第一轮时候返回栈底元素 1
            return last;
        }
    }

    public static void main(String[] args) {
        // Stack继承Vector，默认容量是10
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
