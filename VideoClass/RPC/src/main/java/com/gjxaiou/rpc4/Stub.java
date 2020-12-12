package com.gjxaiou.rpc4;

import com.gjxaiou.IUserService;
import com.gjxaiou.User;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    static IUserService getStub() {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8088);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                /**
                 * 通用化的改动！！！
                 * 直接将方法名称和方法参数，直接传输到服务器端就行，服务器就知道调用什么方法了，让服务器处理。
                 */
                // 把方法名称写过去
                oos.writeUTF(method.getName());
                // 把方法参数类型也写过去，防止重载
                oos.writeObject(method.getParameterTypes());
                // 把方法参数写过去
                oos.writeObject(args);
                oos.flush();

                //接收服务端返回的结果,object读入
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User) ois.readObject();

                return user;
            }
        };

        Object o = Proxy.newProxyInstance(IUserService.class.getClassLoader(),
                new Class[]{IUserService.class}, h);
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);
        return (IUserService) o;
    }
}
