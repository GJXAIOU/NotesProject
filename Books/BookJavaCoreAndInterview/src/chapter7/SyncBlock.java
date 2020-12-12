public class SyncBlock implements Runnable {
	public void run() {
		//
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + ",count:"
						+ i);
			}
		}

	}

	public static void main(String[] args) {
		SyncBlock t1 = new SyncBlock();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		ta.start();
		tb.start();
	}
}
