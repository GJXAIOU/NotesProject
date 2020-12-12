package com.gjxaiou.rpc5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    // 返回值变成了 Object
    static Object getStub(Class clazz) {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8088);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //添加了服务类型的传输
                oos.writeUTF(clazz.getName());//调用服务接口名称
                oos.writeUTF(method.getName());//方法名
                oos.writeObject(method.getParameterTypes());//方法参数类型
                oos.writeObject(args);//方法参数
                oos.flush();

                //接收服务端返回的结果,object读入
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object obj = ois.readObject();

                return obj;//改为返回通用对象
            }
        };

        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, h);
        //这里要写成通用的c，而不是固定的接口
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);
        return o;
    }
}
