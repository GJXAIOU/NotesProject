package com.gjxaiou.easy.day03;

import java.util.Stack;

public class GetMinStack {
    /**
     * @author GJXAIOU
     * 该方法中当新加入的元素大于原来 Min 栈的栈顶元素时候，不动；
     */
    public static class MyStack1 {
        // 分别为数据栈和最小值栈
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        // 因为每次都是创建新的栈，因此使用构造函数
        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        // 生成最小元素栈
        public void push(int newNum) {
            // 如果最小元素栈中没有元素，就将新加入的元素同时压入最小栈，否则需要比较当前数和最小栈中的地栈顶比较，返回最小
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            // Data 栈肯定压入最新的数
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        // peek() 返回 min 栈栈顶，但是不弹出；
        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }


    /**
     * 下面方法和上面的唯一区别就是当新压入的数据大于原来 Min栈中栈顶的时候，将 Min 栈顶的元素再次压入；
     */
    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        System.out.println("=====方法一获得的实时最小值和 pop 方法测试========");
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println("加入 3 之后栈中最小值：" + stack1.getmin());
        stack1.push(4);
        System.out.println("加入 4 之后栈中最小值：" + stack1.getmin());
        stack1.push(1);
        System.out.println("加入 1 之后栈中最小值：" + stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println("弹出栈头之后最小值：" + stack1.getmin());

        System.out.println("=====方法二获得的实时最小值和 pop 方法测试========");
        MyStack2 stack2 = new MyStack2();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }
}
