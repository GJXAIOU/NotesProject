package com.gjxaiou.volatileUse;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyTest3 {
	public static volatile int counter = 0;

	public static void increment() {
		counter++;
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(100);
		IntStream.range(0, 100).forEach(i -> new Thread(() -> {
			IntStream.range(0, 1000).forEach(j -> increment());
			countDownLatch.countDown();
		}).start());
		countDownLatch.await();
		System.out.println(counter);
	}
}
