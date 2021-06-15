package com.gjxaiou.object.productAndConsumer.continueOperate;

import java.util.ArrayList;
import java.util.List;

public class More2More {
	public static void main(String[] args) throws InterruptedException {
		Box box = new Box();
		SetService setService = new SetService(box);
		for (int i = 0; i < 2; i++) {
			new SetValueThread(setService).start();
		}

		Thread.sleep(50);
		new SetCheckThread(setService).start();

		Thread.sleep(10000);
		GetService getService = new GetService(box);
		for (int i = 0; i < 10; i++) {
			new GetValueThread(getService).start();
		}
		Thread.sleep(50);
		new GetCheckThread(getService).start();
	}
}

// 操作的容器
class Box {
	private static List list = new ArrayList();

	synchronized public void add() {
		if (size() < 50) {
			list.add("gjxaiou");
			System.out.println(Thread.currentThread().getName() + " 执行 add() 方法，size 大小为： " + size());
		}
	}

	synchronized public int size() {
		return list.size();
	}

	synchronized public Object popFirst() {
		Object value = list.remove(0);
		System.out.println(Thread.currentThread().getName() + " 执行 popFirst() 方法，size 大小为： " + size());
		return value;
	}
}

// 生产者和消费者业务代码
class SetService {
	private Box box;

	public SetService(Box box) {
		this.box = box;
	}

	public void setMethod() {
		try {
			synchronized (this) {
				while (box.size() == 50) {
					System.out.println("●●●●●●●●●●●●");
					this.wait();
				}
			}
			Thread.sleep(300);
			box.add();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkBoxStatus() {
		try {
			while (true) {
				synchronized (this) {
					if (box.size() < 50) {
						this.notifyAll();
					}
				}
				System.out.println("set checkboxBox = " + box.size());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class GetService {
	private Box box;

	GetService(Box box) {
		this.box = box;
	}

	public void getMethod() {
		try {
			synchronized (this) {
				while (box.size() == 0) {
					System.out.println("○○○○○○○○○○");
					this.wait();
				}
				box.popFirst();
			}
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void checkBoxStatus() {
		try {
			while (true) {
				synchronized (this) {
					if (box.size() > 0) {
						this.notifyAll();
					}
				}
				System.out.println("get checkboxBox = " + box.size());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


// 生产者和消费者线程
class SetValueThread extends Thread {
	private SetService setService;

	SetValueThread(SetService setService) {
		this.setService = setService;
	}

	@Override
	public void run() {
		while (true) {
			setService.setMethod();
		}
	}
}

class GetValueThread extends Thread {
	private GetService getService;

	public GetValueThread(GetService getService) {
		this.getService = getService;
	}

	@Override
	public void run() {
		while (true) {
			getService.getMethod();
		}
	}
}


// 生产者和消费者容器大小测试线程
class SetCheckThread extends Thread {
	private SetService setService;

	SetCheckThread(SetService setService) {
		this.setService = setService;
	}

	@Override
	public void run() {
		setService.checkBoxStatus();
	}
}

class GetCheckThread extends Thread {
	private GetService getService;

	GetCheckThread(GetService getService) {
		this.getService = getService;
	}

	@Override
	public void run() {
		getService.checkBoxStatus();
	}
}