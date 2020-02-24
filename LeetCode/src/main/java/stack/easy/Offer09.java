package stack.easy;

import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 16:17
 */
public class Offer09 {
    class CQueue {
        Stack<Integer> pushStack;
        Stack<Integer> popStack;

        public CQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void appendTail(int value) {
            pushStack.push(value);
        }

        // 删除就是如果 pop 栈不为空弹出头，如果为空则将 push 栈中所有元素一次性倒入 pop 栈中，然后弹出。
        public int deleteHead() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                return -1;
            } else if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
        }
    }

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
}
