package com.gjxaiou.reentrantLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest4 {
	public static void main(String[] args) {
		MyService myService = new MyService();
		new ThreadA(myService).start();
		new ThreadA(myService).start();
	}
}

class MyService {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private String username = "abc";

	public void method() {
		try {
			lock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " begin act,time = " + System.currentTimeMillis());
			System.out.println("service act, username = " + username);
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName() + " end act, time = " + System.currentTimeMillis());
			lock.readLock().unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadA extends Thread {
	private MyService myService;

	public ThreadA(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.method();
	}
}
