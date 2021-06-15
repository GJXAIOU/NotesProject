package com.gjxaiou.object;

import java.util.stream.IntStream;

public class MyTest3 {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		Object lock = new Object();
		IntStream.range(0,10).forEach(i -> new Thread(() ->{
			service.waitMethod(lock);
		}).start());

		Thread.sleep(2000);
		IntStream.range(0,10).forEach(i -> new Thread(() -> {
			service.notifyMethod(lock);
		}).start());

	}
}

class Service{
	public void waitMethod(Object lock){
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

	public void notifyMethod(Object lock){
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


