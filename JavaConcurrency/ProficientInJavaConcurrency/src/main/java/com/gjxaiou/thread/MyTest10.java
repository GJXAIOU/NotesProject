package com.gjxaiou.thread;

public class MyTest10 {
	public static void main(String[] args) {
		MyThread10 myThread10 = new MyThread10();
		myThread10.start();
		try {
			// 主线程只 sleep 100ms，下面的 thread 则 sleep 20000
			Thread.sleep(100);
			myThread10.interrupt();
		} catch (InterruptedException e) {
			System.out.println("进入 main catch 中");
			e.printStackTrace();
		}
		System.out.println("happy end");
	}
}

class MyThread10 extends Thread {
	@Override
	public void run() {
		System.out.println("run begin");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println("sleep() 中被停止，进入 catch " + this.isInterrupted());
			e.printStackTrace();
		}
	}
}
