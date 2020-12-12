package chapter7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyThread extends Thread {
	private String name;

	public MyThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Thread: " + name + " start");
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " finish");
	}
}

public class SimpleThreadPoolDemo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 200,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

		for (int i = 0; i < 5; i++) {
			MyThread myTask = new MyThread(Integer.valueOf(i).toString());
			executor.execute(myTask);			
		}
		executor.shutdown();
	}
}
