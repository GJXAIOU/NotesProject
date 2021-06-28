package com.gjxaiou.singleton;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MyTest2 {
	public static void main(String[] args) throws InterruptedException {

		while (true) {
			CountDownLatch latch = new CountDownLatch(1);
			CountDownLatch end = new CountDownLatch(100);

			for (int i = 0; i < 100; i++) {
				new Thread(() -> {
					try {
						latch.await();
						OneInstance instance = OneInstance.getInstance();
						if (instance.state == 0) {
							System.out.println("instance.state == 0 进程结束");
							System.exit(0);
						}
						end.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			}
			latch.countDown();
			end.await();
			OneInstance.reset();
		}
	}
}

class OneInstance {
	public int state = 0;
	private static OneInstance oneInstance;

	private OneInstance() {
		state = new Random().nextInt(200) + 1;
	}

	public static OneInstance getInstance() {
		if (oneInstance == null) {
			synchronized (OneInstance.class) {
				if (oneInstance == null) {
					oneInstance = new OneInstance();
				}
			}
		}
		return oneInstance;
	}

	public static void reset() {
		oneInstance = null;
	}
}