package com.gjxaiou.object.productAndConsumer.operateList;


import java.util.ArrayList;
import java.util.List;

public class One2One {
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		Product product = new Product(myStack);
		Consumer consumer = new Consumer(myStack);
		new ProductThread(product).start();
		new ConsumerThread(consumer).start();
	}
}

// 操作值
class MyStack {
	private List list = new ArrayList();

	synchronized public void push() {
		try {
			if (list.size() == 1) {
				this.wait();
			}
			list.add(Math.random());
			this.notify();
			System.out.println("push 完成，list 大小为：" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public String pop() {
		String result = "";
		try {
			if (list.size() == 0) {
				System.out.println("pop 中的 " + Thread.currentThread().getName() + " 线程是 wait 状态");
				this.wait();
			}
			result = list.get(0).toString();
			list.remove(0);
			this.notify();
			System.out.println("pop 完成，list 大小为：" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

// 生产者和消费者
class Product {
	private MyStack myStack;

	Product(MyStack myStack) {
		this.myStack = myStack;
	}

	public void pushService() {
		myStack.push();
	}
}

class Consumer {
	private MyStack myStack;

	Consumer(MyStack myStack) {
		this.myStack = myStack;
	}

	public void popService() {
		myStack.pop();
	}
}

// 生产者和消费者线程
class ProductThread extends Thread {
	private Product product;

	ProductThread(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		while (true) {
			product.pushService();
		}
	}
}

class ConsumerThread extends Thread {
	private Consumer consumer;

	ConsumerThread(Consumer consumer) {
		this.consumer = consumer;
	}

	@Override
	public void run() {
		while (true) {
			consumer.popService();
		}
	}
}