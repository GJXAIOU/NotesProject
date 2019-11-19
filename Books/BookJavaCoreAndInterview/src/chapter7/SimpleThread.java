package chapter7;

public class SimpleThread extends Thread {
	// 线程编号
	int index;

	// 通过构造函数指定该线程的编号
	public SimpleThread(int index) {
		this.index = index;
		System.out.println("Create Thread[" + index + "]");
	}

	// run方法，当调用线程的start方法时会自动调用该方法，此时线程进入可执行状态
	@Override
	public void run() {
		for (int j = 0; j <= 3; j++) {
			System.out.println("Thread[" + index + "]:running time " + j);
		}
		// 当前线程运行结束
		System.out.println("Thread[" + index + "] finish");
	}

	public static void main(String args[]) {
	    int threadCount = 3;
		for (int j = 0; j < threadCount; j++) {
			// 实例化该类型的对象，并直接调用start方法直接把线程拉起
			// 这个方法会自动调用run方法
			Thread t = new SimpleThread(j + 1);
			t.start();
		}
	}
}
/**
 * output:
 * Create Thread[1]
 * Create Thread[2]
 * Create Thread[3]
 * Thread[1]:running time 0
 * Thread[2]:running time 0
 * Thread[1]:running time 1
 * Thread[1]:running time 2
 * Thread[1]:running time 3
 * Thread[3]:running time 0
 * Thread[2]:running time 1
 * Thread[2]:running time 2
 * Thread[2]:running time 3
 * Thread[2] finish
 * Thread[3]:running time 1
 * Thread[1] finish
 * Thread[3]:running time 2
 * Thread[3]:running time 3
 * Thread[3] finish
 */
