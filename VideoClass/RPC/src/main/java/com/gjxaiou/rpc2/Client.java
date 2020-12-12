package com.gjxaiou.rpc2;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        Stub stub = new Stub();
        System.out.println(stub.findUserById(123));
    }
}
