class MyThreadLocal {
	// 定义了一个ThreadLocal变量，用来保存当前线程私有数据
	private ThreadLocal<Integer> localVal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};

	public Integer add() {
		// 将值加1，并更新
		localVal.set(localVal.get() + 1);
		return localVal.get() + 1;
	}
}

class UserLocalvALThread extends Thread {
	private MyThreadLocal localObj = new MyThreadLocal();

	public UserLocalvALThread(MyThreadLocal localObj ) {
		this.localObj = localObj ;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "\t"
					+ localObj.add());
		}
	}
}

public class ThreadLocalDemo {
	public static void main(String[] args) {
		MyThreadLocal threadLocal = new MyThreadLocal();
		Thread t1 = new UserLocalvALThread(threadLocal);
		Thread t2 = new UserLocalvALThread(threadLocal);
		Thread t3 = new UserLocalvALThread(threadLocal);
		t1.start();
		t2.start();
		t3.start();

	}
}
