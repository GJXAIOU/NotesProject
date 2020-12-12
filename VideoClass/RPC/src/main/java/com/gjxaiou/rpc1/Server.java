package com.gjxaiou.rpc1;

import com.gjxaiou.IUserService;

import com.gjxaiou.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {
        // 对这个端口进行监听，别人可以通过这个端口连接我
        ServerSocket server = new ServerSocket(8088);
        while (running) {
            // 接收连接并对连接进行处理
            Socket client = server.accept();
            process(client);
            client.close();
        }
        server.close();
    }

    public static void process(Socket socket) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // 读
        int id = dis.readInt();
        IUserService service = new IUserServiceImpl();
        // 获取 user 对象
        User user = service.findUserById(id);
        // 写回去，这里直接将这个类的属性写出去了
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
