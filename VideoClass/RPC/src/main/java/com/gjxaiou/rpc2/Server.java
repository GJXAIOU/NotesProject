package com.gjxaiou.rpc2;

import com.gjxaiou.User;
import com.gjxaiou.IUserService;
import com.gjxaiou.rpc1.IUserServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//unchanged
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

    public static void process(Socket socket) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int id = dis.readInt();
        IUserService service = new IUserServiceImpl();
        User user = service.findUserById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
