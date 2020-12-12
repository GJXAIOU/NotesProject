package com.gjxaiou.rpc4;

import com.gjxaiou.IUserService;
import com.gjxaiou.rpc1.IUserServiceImpl;
import com.gjxaiou.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8088);
        while (running) {
            Socket client = server.accept();
            process(client);
            client.close();
        }
        server.close();
    }

    /**
     * 服务器端也要进行改进
     */
    public static void process(Socket socket) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // 为了适应客户端通用化而做的改动，读取方法名称，方法参数等等
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] parameters = (Object[]) ois.readObject();

        // 服务类型暂时还是写死的，不够灵活
        IUserService service = new IUserServiceImpl();
        // 通过反射方式找到这个方法。
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        // 同时不再直接返回 Id,username 等等参数，而是直接构造好对象方法，防止这个对象 User里面参数和实现变化。
        User user = (User) method.invoke(service, parameters);
        oos.writeObject(user);
        oos.flush();
    }
}
