package com.gjxaiou.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
    // 创建一个真实对象
    private Object sub;
    // 将真实对象作为参数传入构造方法
    public DynamicSubject(Object obj){
        this.sub = obj;
    }

    // 对真实对象的调用都会通过动态代理中 invoke 方法来执行
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling: " + method);
        // 对于真正目标方法的调用
        method.invoke(this.sub, args);
        System.out.println("after calling: " + method);
        return null;
    }
}
