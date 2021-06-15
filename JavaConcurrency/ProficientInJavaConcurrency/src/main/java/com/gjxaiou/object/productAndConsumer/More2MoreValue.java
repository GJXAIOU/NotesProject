package com.gjxaiou.object.productAndConsumer;

public class More2MoreValue {
	public static void main(String[] args) throws InterruptedException {
		String lock = "";
		Product1 product1 = new Product1(lock);
		Consumer1 consumer1 = new Consumer1(lock);

		ThreadProduct1[] threadProduct1s = new ThreadProduct1[2];
		ThreadConsumer1[] threadConsumer1s = new ThreadConsumer1[2];

		for (int i = 0; i < 2; i++) {
			threadProduct1s[i]  = new ThreadProduct1(product1);
			threadProduct1s[i].setName("生产者：" + (i + 1));

			threadConsumer1s[i]  = new ThreadConsumer1(consumer1);
			threadConsumer1s[i].setName("消费者：" + (i + 1));

			threadProduct1s[i].start();
			threadConsumer1s[i].start();
		}

		Thread.sleep(5000);
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);

		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + "----" + threadArray[i].getState());
		}
	}

}


// 存储值的对象
class ObjectValue1 {
	public static String value = "";
}

// 生产者和消费者
class Product1 {
	private String lock;

	public Product1(String lock) {
		this.lock = lock;
	}

	public void setValue() {
		try {
			synchronized (lock) {
				while (!ObjectValue.value.equals("")) {
					System.out.println("product 进入等待 waiting 状态," + Thread.currentThread().getName());
					lock.wait();
				}
				System.out.println("product 进入 Runnable 状态，" + Thread.currentThread().getName());
				ObjectValue.value = System.currentTimeMillis() + "--" + System.nanoTime();
				System.out.println("product 设置值为： " + ObjectValue.value);
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Consumer1 {
	private String lock;

	public Consumer1(String lock) {
		this.lock = lock;
	}

	public void getValue() {
		try {
			synchronized (lock) {
				while (ObjectValue.value.equals("")) {
					System.out.println("consumer 进入等待 waiting 状态," + Thread.currentThread().getName());
					lock.wait();
				}
				System.out.println("consumer 进入 Runnable 状态，" + Thread.currentThread().getName());
				System.out.println("consumer 获取到值为： " + ObjectValue.value);
				ObjectValue.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

// 生成者和消费者线程
class ThreadProduct1 extends Thread {
	private Product1 product;

	ThreadProduct1(Product1 product) {
		this.product = product;
	}

	@Override
	public void run() {
		while (true) {
			product.setValue();
		}
	}
}

class ThreadConsumer1 extends Thread {
	private Consumer1 consumer;

	ThreadConsumer1(Consumer1 consumer) {
		this.consumer = consumer;
	}

	@Override
	public void run() {
		while (true) {
			consumer.getValue();
		}
	}
}