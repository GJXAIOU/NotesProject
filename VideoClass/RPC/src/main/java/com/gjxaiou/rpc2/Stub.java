package com.gjxaiou.rpc2;

import com.gjxaiou.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端代理
 * 作用：连接网络、把 Id 写出去，然后接受对象，最后关掉。
 */
public class Stub {
    public User findUserById(int id) throws IOException {
        // 1.存放服务端地址信息
        Socket socket = new Socket("127.0.0.1", 8088);
        // 2.将客户端的请求信息打包成网络消息（二进制）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        // 注意这里是写死的，缺少灵活性
        dos.writeInt(123);

        // 3.发送给服务器要查询的id
        socket.getOutputStream().write(baos.toByteArray());
        socket.getOutputStream().flush();

        // 4.接收服务端返回的结果，并组装成客户端想要的结果
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int idtmp = dis.readInt();
        if (idtmp != id) {
            System.out.println("error");
        }
        String name = dis.readUTF();
        User user = new User(id, name);
        return user;
    }
}
