package com.gjxaiou.rpc1;

import com.gjxaiou.User;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8088);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        /**
         * 因为无论是什么数据或者协议，最终在网络上传输的都是二进制
         * 注意这里是写死的，缺少灵活性，这里是将 123 通过 DataOutputStream 转换为二进制之后放入 ByteArrayOutputStream（就是一个
         *      byte[] 数组），服务器端通过 DataInputStream 读取并通过后来的 readInt 方法转换为 int 类型。
         */
        dos.writeInt(123);

        // 发送出要查询的 id，写出去。
        socket.getOutputStream().write(baos.toByteArray());
        socket.getOutputStream().flush();

        //接收服务端返回的结果
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // 读取各个属性再转换为对象
        int id = dis.readInt();
        String name = dis.readUTF();
        User user = new User(id, name);
        System.out.println(user);
        dos.close();
        socket.close();
    }
}
