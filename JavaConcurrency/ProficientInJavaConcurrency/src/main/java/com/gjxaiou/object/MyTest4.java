package com.gjxaiou.object;


public class MyTest4 {
	public static void main(String[] args) throws InterruptedException {
		Service2 service2 = new Service2();
		for (int i = 0; i < 10; i++) {
			Thread1 thread1 =  new Thread1(service2);
			thread1.start();
		}

		Thread.sleep(1000);

		Thread2 thread2 = new Thread2(service2);
		thread2.start();
		Thread.sleep(500);
		Thread2 thread3 = new Thread2(service2);
		thread3.start();
		Thread.sleep(500);
		Thread2 thread4 = new Thread2(service2);
		thread4.start();
		Thread.sleep(500);
		Thread2 thread5 = new Thread2(service2);
		thread5.start();
		Thread.sleep(500);


	}
}



class Service2{
	private Object lock = new Object();
	public void waitMethod(){
		synchronized (lock) {
			try {
				System.out.println(Thread.currentThread().getName() + " begin wait");
				lock.wait();
				System.out.println(Thread.currentThread().getName() + " end wait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void notifyMethod(){
		synchronized (lock){
			try {
				System.out.println(Thread.currentThread().getName() + " begin notify");
				lock.notify();
				System.out.println(Thread.currentThread().getName() + " end notify");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}

class  Thread1 extends Thread{
	private Service2 service2;

	public Thread1(Service2 service2) {
		this.service2 = service2;
	}

	@Override
	public void run() {
		service2.waitMethod();
	}
}
class  Thread2 extends Thread{
	private Service2 service2;

	public Thread2(Service2 service2) {
		this.service2 = service2;
	}

	@Override
	public void run() {
		service2.notifyMethod();
	}
}


