package com.gjxaiou.rpc4;

import com.gjxaiou.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service = Stub.getStub();
        System.out.println(service.findUserById(123));
    }
}
