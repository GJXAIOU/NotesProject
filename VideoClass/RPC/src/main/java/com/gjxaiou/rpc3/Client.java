package com.gjxaiou.rpc3;

import com.gjxaiou.IUserService;

public class Client {
    public static void main(String[] args) {
        // 获取代理对象
        IUserService stub = Stub.getStub();
        System.out.println(stub.findUserById(123));
    }
}
