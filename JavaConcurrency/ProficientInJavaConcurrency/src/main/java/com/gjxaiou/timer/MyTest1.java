package com.gjxaiou.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest1 {
	public static void main(String[] args) throws InterruptedException {
		// 预定执行的时间
		System.out.println("当前时间为：" + System.currentTimeMillis());
		long earlyTime = System.currentTimeMillis() - 2000;
		long latterTime = System.currentTimeMillis() + 2000;

		Task task1 = new Task();
		Task task2 = new Task();
		Timer timer = new Timer();
		System.out.println("计划执行时间为：" + earlyTime);
		timer.schedule(task1, new Date(earlyTime));
		System.out.println("计划执行时间为：" + latterTime);
		timer.schedule(task2, new Date(latterTime));

		Thread.sleep(Integer.MAX_VALUE);
	}
}

// 封装要执行的任务
class Task extends TimerTask {
	@Override
	public void run() {
		try {
			System.out.println("开始执行任务啦，真正时间为：" + System.currentTimeMillis());
			Thread.sleep(4000);
			System.out.println("结束执行任务啦，真正时间为：" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
