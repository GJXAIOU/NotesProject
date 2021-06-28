package com.gjxaiou.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest4 {
	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		System.out.println("当前时间为：" + currentTime);
		long executeTime = currentTime - 2000;
		System.out.println("计划执行时间：" + executeTime);
		Timer timer = new Timer();
		MyTask3 myTask3 = new MyTask3();
		//	timer.schedule(myTask3, new Date(executeTime), 2000);
		timer.scheduleAtFixedRate(myTask3, new Date(executeTime), 2000);
	}
}

class MyTask3 extends TimerTask {
	@Override
	public void run() {
		try {
			System.out.println("begin time：" + System.currentTimeMillis());
			Thread.sleep(1000);
			System.out.println("end time: " + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

