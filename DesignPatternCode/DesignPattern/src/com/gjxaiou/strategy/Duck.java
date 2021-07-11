package com.gjxaiou.strategy;

public abstract class Duck {

    public Duck() {

    }

    /**
     * ��ʾѼ����Ϣ
     */
    public abstract void display();

    public void quack() {
        System.out.println("Ѽ�Ӹ¸½�~~");
    }

    public void swim() {
        System.out.println("Ѽ�ӻ���Ӿ~~");
    }

    public void fly() {
        System.out.println("Ѽ�ӻ����~~~");
    }
}
