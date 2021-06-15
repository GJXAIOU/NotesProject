package com.gjxaiou.thread;

public class MyTest1 {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.setName("新创建的线程 Thread - 0 ");
		myThread.start();
		 //myThread.run();
	}
}

class MyThread extends Thread {
	String threadName;

	MyThread() {
		System.out.println("执行构造方法线程：" + Thread.currentThread().getName());
		System.out.println("构造方法：" + this.getName());
	}

	@Override
	public void run() {
		System.out.println("执行 run 方法线程： " + Thread.currentThread().getName());
		System.out.println("run 方法：" + this.getName());
	}

	public String getThreadName() {
		return this.threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
