package com.gjxaiou.synchronize;

public class MyTest12 {
	public static void main(String[] args) {
		try {
			UserInfo userInfo = new UserInfo();
			DemoService demoService = new DemoService();
			// 两个线程持有的锁对象是同一个，仅仅是对象的属性改变了，但是对象未发生改变
			new Thread(() -> demoService.hello(userInfo)).start();
			Thread.sleep(20);
			new Thread(() -> demoService.hello(userInfo)).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class DemoService {
	public void hello(UserInfo userInfo) {
		synchronized (userInfo) {
			try {
				System.out.println(Thread.currentThread().getName() + " 执行开始");
				userInfo.setName("zhangsan");
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName() + " 执行结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class UserInfo {
	String name;
	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


