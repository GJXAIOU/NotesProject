package nowcoder.easy.day03;

public class ArrayToStackAndQueue {

	/**
	 * 数组结构实现大小固定的队列
	 */
	public static class ArrayToStack {
		private Integer[] arr;
		// index 当前指向栈的位置： 0 ~ size -1
		private Integer index;

		// 初始化数组
		public ArrayToStack(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			index = 0;
		}

		/**
		 * 实现在栈中压入一个数
		 * @param obj：要压入的数
		 *           index：指向的是栈中下一个有空位置的数组下标
		 */
		public void push(int obj) {
			if (index == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			// index 位置填上，然后 index++
			arr[index++] = obj;
		}

		// 弹出一个栈顶的元素
		public Integer pop() {
			if (index == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--index];
		}

		// 只将值返回给我，但是原来栈中该值仍然保存
		public Integer peek() {
			if (index == 0) {
				return null;
			}
			return arr[index - 1];
		}
	}


	/**
	 * 数组结构实现大小固定的队列
	 */
	public static class ArrayToQueue {
		private Integer[] arr;
		private Integer size;
		private Integer start;
		private Integer end;

		// 初始化队列
		public ArrayToQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			start = 0;
			end = 0;
		}

		/**
		 * 向队列中放入一个数
		 * @param obj：需要放进去的数
		 */
		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			size++;
			// 该数放在 end 的位置上，因为 end 位置是上下移动的；
			arr[end] = obj;
			// end 如果到底即 Length-1，就等于 0，从头开始写入数据，可以覆盖之前的元素；如果没有到底就 end + 1;
			end = (end == arr.length - 1) ? 0 : end + 1;
		}

		// 弹出栈顶元素
		public Integer poll() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;
			// 因为 start 位置要改变，所有使用临时变量 tmp 记录一下 start 位置，最终弹出的是原始 start位置元素；
			int tmp = start;
			start = (start == arr.length - 1) ? 0 : start + 1;
			return arr[tmp];
		}

		// 取出栈顶的元素，但是栈顶不动
		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[start];
		}
	}

	public static void main(String[] args) {

	}
}
