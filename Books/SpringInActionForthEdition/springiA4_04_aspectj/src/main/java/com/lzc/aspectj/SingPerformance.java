package com.lzc.aspectj;

public class SingPerformance implements Performance {

	@Override
	public void perform() {
		System.out.println("表演中...");
	}
}
