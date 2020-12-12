package com.gjxaiou.concurrency.example.publish;

import com.gjxaiou.concurrency.annoations.NotRecommend;
import com.gjxaiou.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        /** this 引用溢出
         Escape 的构造方法中有 InnerClass 对象，同时 InnerClass 内部可以使用 Escape 的 this 引用，
         如果 this 引用在构造方法中，被别的对象使用到，这样的 this 引用所指的对象被认为是没有正确构建的。
         */
        // 内部类实例
        new InnerClass();
    }

    /**
     * 包含了对封装实例的隐藏和引用，这样
     * TODO:在对象没有被正确构造完成之前就会被发布，由此导致不安全的因素在里面
     * 1. 导致 this 引用在构造期间溢出的错误，他是在构造函数构造过程中启动了一个线程，造成 this 引用的溢出
     * 2. 新线程只是在对象构造完毕之前就已经看到他了，所以如果要在构造函数中创建线程，那么不要启动它，
     * 而是应该用一个专有的 start，或是其他的方式统一启动线程
     * 3. 使用工厂方法和私有构造函数来完成对象创建和监听器的注册来避免不正确的发布
     */
    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
