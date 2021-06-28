package com.gjxaiou.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest2 {
	public static void main(String[] args) {
		long now = System.currentTimeMillis();
		System.out.println("当前时间：" + now);
		MyTask1 myTask1 = new MyTask1();
		MyTask2 myTask2 = new MyTask2();
		Timer timer = new Timer();
		timer.schedule(myTask1, new Date(now), 4000);
		timer.schedule(myTask2, new Date(now), 4000);
	}
}

class MyTask1 extends TimerTask {
	@Override
	public void run() {
		System.out.println("MyTask1 开始执行，时间为：" + System.currentTimeMillis());
		this.cancel();
		System.out.println("MyTask1 任务自己移除自己");
	}
}

class MyTask2 extends TimerTask {
	@Override
	public void run() {
		System.out.println("MyTask2 开始执行，时间为：" + System.currentTimeMillis());
	}
}
