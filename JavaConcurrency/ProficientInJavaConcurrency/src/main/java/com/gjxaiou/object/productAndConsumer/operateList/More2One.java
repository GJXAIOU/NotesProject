package com.gjxaiou.object.productAndConsumer.operateList;


public class More2One {
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		Product product1 = new Product(myStack);
		Product product2 = new Product(myStack);
		Product product3 = new Product(myStack);

		new ProductThread(product1).start();
		new ProductThread(product2).start();
		new ProductThread(product3).start();

		Consumer consumer = new Consumer(myStack);
		new ConsumerThread(consumer).start();
	}
}
