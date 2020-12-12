package com.gjxaiou.rpc1;

import com.gjxaiou.IUserService;
import com.gjxaiou.User;

public class IUserServiceImpl implements IUserService {
    //直接new模拟数据库查询
    @Override
    public User findUserById(int id) {
        return new User(id, "Alice");
    }
}
