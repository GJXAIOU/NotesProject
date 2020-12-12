package com.gjxaiou.bytecode;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        RealSubject rs = new RealSubject();
        DynamicSubject ds = new DynamicSubject(rs);
        // 获取 class 对象，因为后面创建动态代理的类需要类加载器，然后通过 class 对象和类加载器创建对象
        Class<?> cls = rs.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(),
                cls.getInterfaces(), ds);

        subject.request();
        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass());
    }
}

/** output:
 *  before calling: public abstract void com.gjxaiou.bytecode.Subject.request()
 *  From real subject
 *  after calling: public abstract void com.gjxaiou.bytecode.Subject.request()
 *  class com.sun.proxy.$Proxy0
 *  class java.lang.reflect.Proxy
 */
