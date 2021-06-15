package com.gjxaiou.lock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTest2 {
	public static void main(String[] args) throws InterruptedException {
		// MyService myService = new MyService(true);
		// 非公平锁
		MyService myService	 = new MyService(false);

		MyThread[] array1 = new MyThread[4];
		MyThread[] array2 = new MyThread[4];
		for (int i = 0; i < array1.length; i++) {
			array1[i] = new MyThread(myService);
			array1[i].setName("array1 -- " + (i + 1));
		}
		Arrays.stream(array1).forEach(myThread -> {
			myThread.start();
		});

		for (int i = 0; i < array2.length; i++) {
			array2[i] = new MyThread(myService);
			array2[i].setName("array2 -- " + (i + 1));
		}
		Thread.sleep(500);
		Arrays.stream(array2).forEach(myThread -> myThread.start());
	}
}

// 线程类
class MyThread extends Thread {
	private MyService myService;

	MyThread(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.method();
	}
}

class MyService {
	public Lock lock;

	public MyService(boolean fair) {
		lock = new ReentrantLock(fair);
	}

	public void method() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " 执行 method");
		try {
			// 配合 main 方法是，使得 array2 中线程有机会在非公平情况下获取锁
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
