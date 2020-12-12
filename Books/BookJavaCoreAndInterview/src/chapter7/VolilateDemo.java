public class VolilateDemo extends Thread {
	public static volatile int cnt = 0;

	public static void add() {
		// 这里延迟1毫秒，增加多线程并发抢占的概率
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
		cnt++;
	}

	public static void main(String[] args) {
		// 同时启动1000个线程，去进行加操作
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				public void run() {
					VolilateDemo.add();
				}
			}).start();
		}

		// 这里每次运行的值都有可能不同
		System.out.println("Result is " + VolilateDemo.cnt);
	}

}
