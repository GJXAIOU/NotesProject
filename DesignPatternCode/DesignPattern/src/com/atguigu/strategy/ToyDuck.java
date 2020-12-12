package com.atguigu.strategy;

public class ToyDuck extends Duck {

    @Override
    public void display() {
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

    @Override
    public void fly() {
        System.out.println("���Ѽ�������~~~");
    }
}
