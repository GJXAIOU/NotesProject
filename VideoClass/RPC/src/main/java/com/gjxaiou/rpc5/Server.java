package com.gjxaiou.rpc5;

import com.gjxaiou.*;
import com.gjxaiou.rpc1.IUserServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private static boolean running = true;
    private static HashMap<String, Class> registerTable = new HashMap<>();

    static {
        registerTable.put(IUserService.class.getName(), IUserServiceImpl.class);//key类型是接口，value
        // 是具体实现类才能完成调用
        registerTable.put(IProductService.class.getName(), IProductServiceImpl.class);
    }

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8088);
        while (running) {
            Socket client = server.accept();
            process(client);
            client.close();
        }
        server.close();
    }

    public static void process(Socket socket) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        //为了适应客户端通用化而做的改动
        // 首先获取服务接口名称，然后方法
        String clazzName = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] parameters = (Object[]) ois.readObject();

        //IUserService service = new IUserServiceImpl();
        // 进化1：本来是硬编码new出来的，现在变成从注册表中查到服务类，如果使用spring甚至还可以直接根据配置注入bean然后根据bean查找。
//        Class clazz = IUserServiceImpl.class;
//        Method method = clazz.getMethod(methodName, parameterTypes);
//        Object o = method.invoke(clazz.newInstance(), parameters);
//
//        oos.writeObject(o);
//        oos.flush();


        Object service = registerTable.get(clazzName).newInstance();
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        Object o = method.invoke(service, parameters);
        oos.writeObject(o);
        oos.flush();
    }
}
