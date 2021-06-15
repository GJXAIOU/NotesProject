package com.gjxaiou.thread;

public class MyTest6 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main 开始执行");
		MyThread6 myThread6 = new MyThread6();
		myThread6.start();
		Thread.sleep(2000);
		System.out.println("main 继续执行....");
	}
}

class MyThread6 extends Thread{
	@Override
	public void run() {
		System.out.println("自定义线程开始执行");
	}
}
