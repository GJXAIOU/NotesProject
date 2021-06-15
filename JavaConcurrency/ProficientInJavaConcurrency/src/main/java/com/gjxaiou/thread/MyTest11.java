package com.gjxaiou.thread;

public class MyTest11 {
	public static void main(String[] args) {
		MyThread11 myThread11 = new MyThread11();
		myThread11.start();
		myThread11.interrupt();
	}

}

class MyThread11 extends Thread {
	@Override
	public void run() {
		System.out.println("run begin");
		try {
			Thread.sleep(2000);
			System.out.println("run end");
		} catch (InterruptedException e) {
			System.out.println("先停止然后进入 sleep，进入了 run catch");
			e.printStackTrace();
		}
	}
}
