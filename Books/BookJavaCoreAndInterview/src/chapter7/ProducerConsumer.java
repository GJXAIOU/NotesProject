class ProductData {
	//表示被哪个生产者线程生产出来的编号
	private int number;
	// 标志位，true表示已经消费
	private boolean flag = true;

	public synchronized void product(int number) {
		if (!flag) {
			try {
				// 未消费等待
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.number = number;
		// 标记已经生产
		flag = false;
		// 通知消费者已经生产，可以消费
		notify();
	}

	public synchronized int consume() {
		if (flag) {
			try {
				// 未生产等待
				wait();
			} catch (InterruptedException e) {
				// 省略报异常的语句
				// …
			}
		}
		// 标记已经消费
		flag = true;
		// 通知需要生产
		notify();
		return this.number;
	}
}

// 生产者线程
class Producer extends Thread {
	private ProductData s;

	Producer(ProductData s) {
		this.s = s;
	}

	public void run() {
		for (int i = 0; i <= 5; i++) {
			s.product(i);
			System.out.println("P[" + i + "] Product.");
		}
	}
}

// 消费者线程
class Consumer extends Thread {
	private ProductData s;

	Consumer(ProductData s) {
		this.s = s;
	}

	public void run() {
		int i;
		do {
			i = s.consume();
			System.out.println("P[" + i + "] Consume.");
		} while (i != 9);
	}
}

public class ProducerConsumer {
	public static void main(String argv[]) {
		ProductData s = new ProductData();
		new Producer(s).start();
		new Consumer(s).start();
	}
}
