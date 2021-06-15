package com.gjxaiou.thread;

public class MyTest3 {
	public static void main(String[] args) {

	}
}

class MyThread2 extends Thread{
	@Override
	public void run() {
		System.out.println("run threadName：" + Thread.currentThread().getName());
	}
}
