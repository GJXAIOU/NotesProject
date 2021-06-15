package com.gjxaiou.thread;

public class MyTest7 {
	public static void main(String[] args) throws InterruptedException {
		MyThread7 myThread7 = new MyThread7();
		myThread7.start();
		Thread.sleep(20);
		myThread7.interrupt();
		System.out.println(myThread7.interrupted());
		System.out.println(myThread7.interrupted());
		System.out.println(myThread7.isInterrupted());
		System.out.println(myThread7.isInterrupted());
	}
}

class MyThread7 extends Thread {
	@Override
	public void run() {
		int i = 0;
		while (i++ < 10000) {
			System.out.println("自定义线程执行");
		}
	}
}