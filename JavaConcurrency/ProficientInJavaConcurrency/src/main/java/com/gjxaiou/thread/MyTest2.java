package com.gjxaiou.thread;

public class MyTest2 {
	public static void main(String[] args) throws InterruptedException {
		MyThread1 myThread1 = new MyThread1();
		System.out.println("begin：" + myThread1.isAlive());
		myThread1.start();
		Thread.sleep(2000);
		System.out.println("end：" + myThread1.isAlive());
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		System.out.println("run 方法：" + this.isAlive());
	}
}
