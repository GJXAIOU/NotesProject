package com.gjxaiou.object.productAndConsumer;

import java.lang.String;

public class One2OneValue {

	public static void main(String[] args) {
		String lock = "";
		Product product = new Product(lock);
		Consumer consumer = new Consumer(lock);
		new ThreadProduct(product).start();
		new ThreadConsumer(consumer).start();
	}

}

// 存储值的对象
class ObjectValue {
	public static String value = "";
}

// 生产者和消费者
class Product {
	private String lock;

	public Product(String lock) {
		this.lock = lock;
	}

	public void setValue() {
		try {
			synchronized (lock) {
				if (!ObjectValue.value.equals("")) {
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

class Consumer {
	private String lock;

	public Consumer(String lock) {
		this.lock = lock;
	}

	public void getValue() {
		try {
			synchronized (lock) {
				if (ObjectValue.value.equals("")) {
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
class ThreadProduct extends Thread {
	private Product product;

	ThreadProduct(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		while (true) {
			product.setValue();
		}
	}
}

class ThreadConsumer extends Thread {
	private Consumer consumer;

	ThreadConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	@Override
	public void run() {
		while (true) {
			consumer.getValue();
		}
	}
}