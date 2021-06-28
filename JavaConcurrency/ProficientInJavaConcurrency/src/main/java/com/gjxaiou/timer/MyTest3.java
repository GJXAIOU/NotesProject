package com.gjxaiou.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest3 {
	public static void main(String[] args) {
		int i = 0;
		long currentTime = System.currentTimeMillis();
		System.out.println("当前时间即计划开始时间: " + currentTime);
		while (true) {
			i++;
			Timer timer = new Timer();
			MyTask myTask = new MyTask(i);
			timer.schedule(myTask, new Date(currentTime));
			timer.cancel();
		}
	}
}

class MyTask extends TimerTask {
	private int i;

	MyTask(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("第 " + i + " 次没有被 cancel 取消");
	}
}
