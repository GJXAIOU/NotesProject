package com.atguigu.strategy.improve;

public class ToyDuck extends Duck{

	
	public ToyDuck() {
		// TODO Auto-generated constructor stub
		flyBehavior = new NoFlyBehavior();
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("���Ѽ");
	}

	//��Ҫ��д��������з���
	
	@Override
    public void quack() {
		System.out.println("���Ѽ���ܽ�~~");
	}
	
	@Override
	public void swim() {
		System.out.println("���Ѽ������Ӿ~~");
	}
	
	
}
