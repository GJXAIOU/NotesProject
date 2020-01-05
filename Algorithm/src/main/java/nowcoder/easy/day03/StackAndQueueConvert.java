package nowcoder.easy.day03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 实现仅用 2 个队列实现栈以及使用 2 个栈实现队列；
 */
public class StackAndQueueConvert {

	public static class StackConvertToQueue {
		private Stack<Integer> stackPush;
		private Stack<Integer> stackPop;

		public StackConvertToQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// 向 push 栈中加入数据
		public void push(int pushInt) {
			stackPush.push(pushInt);
		}

		// 将 push 栈中数据全部倒入 pop 栈中，然后返回 Pop 栈顶元素
		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.pop();
		}

		// 将 push 栈中数据全部倒入 Pop 栈，然后仅仅复制返回 pop 栈顶元素
		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
	}


	/**
	 * 使用两个队列实现栈结构
	 */
	public static class QueueConvertToStack {
		private Queue<Integer> data;
		private Queue<Integer> help;

		public QueueConvertToStack() {
			// 用双向链表实现，也可以使用动态数组
			data = new LinkedList<Integer>();
			help = new LinkedList<Integer>();
		}

		// 压数的时候直接在 data 中将该数压入
		public void push(int pushInt) {
			data.add(pushInt);
		}

		// 实现弹出一个数
		public int pop() {
			if (data.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			// 当 data 队列中不止一个数，将 data中所有数放进 help 中，当 data 中只剩一个数时候停止，然后将该数弹出并返回
			while (data.size() > 1) {
				help.add(data.poll());
			}
			int res = data.poll();
			// 改变两个引用，就是 Help 栈变 data 栈， data栈变  help 栈；
			swap();
			return res;
		}

		public int peek() {
			if (data.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (data.size() != 1) {
				help.add(data.poll());
			}
			int res = data.poll();
			help.add(res);
			swap();
			return res;
		}

		private void swap() {
			Queue<Integer> tmp = help;
			help = data;
			data = tmp;
		}
	}
}
