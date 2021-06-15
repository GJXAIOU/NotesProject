package com.gjxaiou.thread;

public class MyTest8 {
	public static void main(String[] args) {
		Thread.currentThread().interrupt();
		System.out.println(Thread.interrupted());
		System.out.println(Thread.interrupted());
	}
}
