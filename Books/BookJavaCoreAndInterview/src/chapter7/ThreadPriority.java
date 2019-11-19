package chapter7;

//实现Runnbale接口，此时这个类就可以extends其他的父类了
public class ThreadPriority implements Runnable {
	// 线程编号
	int number;

	public ThreadPriority(int num) {
		number = num;
		System.out.println("Create Thread[" + number + "]");
	}

	// run方法，当调用线程的start方法时会调用该方法
	@Override
	public void run() {
		for (int i = 0; i <= 3; i++) {
			System.out.println("Thread[" + number + "]:Count " + i);
		}
	}

	public static void main(String args[]) {
		// 定义线程t1，并设置其优先级为5
		Thread t1 = new Thread(new ThreadPriority(1));
		t1.setPriority(1);
		// 定义线程t2，并设置其优先级为7
		Thread t2 = new Thread(new ThreadPriority(2));
		t2.setPriority(7);
		// 启动这两个线程
		t1.start();
		t2.start();
	}
}
/**
 * output:
 * Create Thread[1]
 * Create Thread[2]
 * Thread[1]:Count 0
 * Thread[2]:Count 0
 * Thread[1]:Count 1
 * Thread[2]:Count 1
 * Thread[1]:Count 2
 * Thread[2]:Count 2
 * Thread[1]:Count 3
 * Thread[2]:Count 3
 */

