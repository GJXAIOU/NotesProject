package com.gjxaiou.strategy.improve;

public abstract class Duck {

    // ����, ���Խӿ�
    FlyBehavior flyBehavior;
    // ��������<->���Խӿ�
    QuackBehavior quackBehavior;

    public Duck() {
    }

    // ��ʾѼ����Ϣ
    public abstract void display();

    public void quack() {
        System.out.println("Ѽ�Ӹ¸½�~~");
    }

    public void swim() {
        System.out.println("Ѽ�ӻ���Ӿ~~");
    }

    public void fly() {
        //�Ľ�
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
