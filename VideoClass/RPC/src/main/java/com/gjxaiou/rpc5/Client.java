package com.gjxaiou.rpc5;

import com.gjxaiou.IProductService;
import com.gjxaiou.IUserService;


public class Client {
    public static void main(String[] args) {
        // 告诉想获取实现 IUserService 类型的对象
        IUserService service = (IUserService) Stub.getStub(IUserService.class);
        // 告诉想获取实现 IProductService 类型的对象
        IProductService service2 = (IProductService)Stub.getStub(IProductService.class);
        System.out.println(service.findUserById(123));
        System.out.println(service2.findProductByName("Bob"));
    }
}
