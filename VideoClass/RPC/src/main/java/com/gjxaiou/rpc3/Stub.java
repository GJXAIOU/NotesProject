package com.gjxaiou.rpc3;

import com.gjxaiou.User;
import com.gjxaiou.IUserService;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    /**
     * 动态产生 IUserService 类。
     * 通过动态代理产生的是实现了 UserService 接口的新的类。
     */
    public static IUserService getStub() {
        /**
         * 方法调用处理器
         * 例如首先有一个接口 A，然后有一个实现了该接口 A 的类 B， B 类又是动态产生的，当调用动态产生类 B 里面的方法的时候如果想加一些内容，就是通过
         *      InvovationHanlder 里面加进去的。因为调用该类 B 中的所有方法的时候，本质上都是调用自己写的 invoke 方法，
         */
        InvocationHandler h = new InvocationHandler() {
            /**
             * @param proxy 代理的对象是谁：就是 B 类的对象
             * @param method 正在调用哪个方法
             * @param args 传入了那些参数
             * @return 先把 123 往外写，然后获取结果构建一个对象返回
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8088);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                // 注意这里是写死的，缺少灵活性
                dos.writeInt(123);

                //发送出要查询的id
                socket.getOutputStream().write(baos.toByteArray());
                socket.getOutputStream().flush();

                //接收服务端返回的结果
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                int id = dis.readInt();
                String name = dis.readUTF();
                Object user = new User(id, name);
                return user;
            }
        };

        /**
         * JDK 动态代理
         * 参数一：产生代理类的 classLoader，参数二：代理类实现了哪些接口，参数三：上面方法调用处理器返回的结果.
         * o 就是动态代理产生的新的对象。
         */
        Object o = Proxy.newProxyInstance(IUserService.class.getClassLoader(),
                new Class[]{IUserService.class}, h);
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);
        return (IUserService) o;
    }
}
