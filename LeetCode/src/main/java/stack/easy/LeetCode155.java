package stack.easy;

import java.time.temporal.ValueRange;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/31 20:05
 */
public class LeetCode155 {
    class MinStack {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            // 如果 minStack 为空直接放入，否则只有比 minStack 栈顶元素小才放入
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else if (x <= getMin()) {
                minStack.push(x);
            }

            // dataStack 一直压入元素
            dataStack.push(x);
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
                Integer value = dataStack.pop();
                // 如果 dataStack 中弹出的值正巧是最小值，则 minStack 栈顶弹出
                if (value == getMin()) {
                    minStack.pop();
                }
            } else {
                throw new RuntimeException("this stack is empty");
            }
        }

        public int top() {
            Integer value = 0;
            if (!dataStack.isEmpty()) {
                value = dataStack.peek();
            } else {
                throw new RuntimeException("this stack is empty");
            }
            return value;
        }

        public int getMin() {
            Integer value = 0;
            if (!minStack.isEmpty()) {
                value = minStack.peek();
            } else {
                throw new RuntimeException("this stack is empty");
            }
            return value;
        }
    }


    // 方法二：
    class MinStack2 {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            // 如果 minStack 为空直接放入，否则只有比 minStack 栈顶元素小才放入
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else if (x < getMin()) {
                minStack.push(x);
            }else {
                Integer peek = minStack.peek();
                minStack.push(peek);
            }

            // dataStack 一直压入元素
            dataStack.push(x);
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
               dataStack.pop();
               // 两个栈高度一样，要一起弹出
               minStack.pop();
            } else {
                throw new RuntimeException("this stack is empty");
            }
        }

        public int top() {
            Integer value = 0;
            if (!dataStack.isEmpty()) {
                value = dataStack.peek();
            } else {
                throw new RuntimeException("this stack is empty");
            }
            return value;
        }

        public int getMin() {
            Integer value = 0;
            if (!minStack.isEmpty()) {
                value = minStack.peek();
            } else {
                throw new RuntimeException("this stack is empty");
            }
            return value;
        }
    }
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
