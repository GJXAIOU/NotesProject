package com.gjxaiou.thread;

import java.util.concurrent.SynchronousQueue;

public class MyTest14 {
	public static void main(String[] args) {
		try {
			Thread2 thread2 = new Thread2();
			new Thread1(thread2).start();

			Thread.sleep(1000);
			new Thread3(thread2).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread1 extends Thread {
	private Thread2 thread2;

	public Thread1(Thread2 thread2) {
		this.thread2 = thread2;
	}

	@Override
	public void run() {
		try {
			synchronized (thread2) {
				thread2.start();
				// 这里要休眠 6s
				Thread.sleep(6000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread2 extends Thread {
	@Override
	public void run() {
		try {
			long beginTime = System.currentTimeMillis();
			System.out.println("thread2 begin run," + beginTime);
			Thread.sleep(5000);
			long endTime = System.currentTimeMillis();
			System.out.println("thread2 end run," + endTime + " -- total time = " + (endTime - beginTime)/1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void thread2Service() {
		System.out.println("执行 thread2Service time = " + System.currentTimeMillis());
	}
}

class Thread3 extends Thread {
	private Thread2 thread2;

	Thread3(Thread2 thread2) {
		this.thread2 = thread2;
	}

	@Override
	public void run() {
		thread2.thread2Service();
	}
}
