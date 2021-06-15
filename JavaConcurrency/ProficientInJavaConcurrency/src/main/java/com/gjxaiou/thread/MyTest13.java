package com.gjxaiou.thread;

import java.lang.String;

public class MyTest13 {
	public static void main(String[] args) {
		try {
			ThreadB threadB = new ThreadB();
			threadB.start();

			Thread.sleep(500);
			ThreadC threadC = new ThreadC(threadB);
			threadC.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class ThreadA extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String string = new String();
			Math.random();
		}
	}
}

class ThreadB extends Thread {
	@Override
	public void run() {
		try {
			ThreadA threadA = new ThreadA();
			threadA.start();
			threadA.join();
			System.out.println("线程 B 在 run end 处打印");
		} catch (InterruptedException e) {
			System.out.println("线程 B 在 catch 中打印了");
			e.printStackTrace();
		}
	}
}

class ThreadC extends Thread {
	private ThreadB threadB;

	ThreadC(ThreadB threadB) {
		this.threadB = threadB;
	}

	@Override
	public void run() {
		threadB.interrupt();
	}
}
