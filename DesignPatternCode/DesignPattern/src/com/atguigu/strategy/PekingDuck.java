package com.atguigu.strategy;

public class PekingDuck extends Duck {

    @Override
    public void display() {
        System.out.println("~~����Ѽ~~~");
    }

    //��Ϊ����Ѽ���ܷ��裬�����Ҫ��дfly
    @Override
    public void fly() {
        System.out.println("����Ѽ���ܷ���");
    }
}
