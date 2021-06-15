package com.gjxaiou.synchronize;

import java.util.ArrayList;
import java.util.List;

public class MyTest10 {
	public static void main(String[] args) throws InterruptedException {
		MyOneList myOneList = new MyOneList();
		new MyThread11(myOneList).start();
		new MyThread12(myOneList).start();
		Thread.sleep(6000);
		System.out.println("listSize = " + myOneList.getSize());
	}
}

class MyOneList {
	List list = new ArrayList();

	synchronized public void add(int data) {
		list.add(data);
	}

	synchronized public int getSize() {
		return list.size();
	}
}

class MyObject {
	public MyOneList addMethod(MyOneList list, int data) {
		try {
			synchronized (list) {
				if (list.getSize() < 1) {
					Thread.sleep(2000);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

// 创建两个线程
class MyThread11 extends Thread {
	MyOneList myOneList;

	MyThread11(MyOneList myOneList) {
		this.myOneList = myOneList;
	}

	@Override
	public void run() {
		MyObject myObject = new MyObject();
		myObject.addMethod(myOneList, 1);
	}
}

class MyThread12 extends Thread {
	MyOneList myOneList;

	MyThread12(MyOneList myOneList) {
		this.myOneList = myOneList;
	}

	@Override
	public void run() {
		MyObject myObject = new MyObject();
		myObject.addMethod(myOneList, 2);
	}
}