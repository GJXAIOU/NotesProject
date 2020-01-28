package com.gjxaiou.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/28 13:16
 */

public class Client {
    public static void main(String[] args) {
        //将动态代理生成的class输出文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
                "true");
        // 我们要代理的真实对象
        //这里指定被代理类
        Subject realSubject = new RealSubject();
        // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicSubject(realSubject);
        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader()，我们这里使用代理类的类加载器，也就是被代理的那个真实对象
         * 第二个参数realSubject.getClass().getInterfaces()
         * ，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler，我们这里将这个代理对象关联到了上方的InvocationHandler 这个对象上
         */
        Subject subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
        subject.request();
    }
}

interface Subject {
    void request();
}

class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real subject");
    }
}

class DynamicSubject implements InvocationHandler {
    //　这个就是我们要代理的真实对象
    private Object subject;

    // 构造方法，给我们要代理的真实对象赋初值
    public DynamicSubject(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable {
        //　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");
        System.out.println("Method:" + method);
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        //　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");
        return null;
    }
}


