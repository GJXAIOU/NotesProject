package com.gjxaiou.action;

import com.gjxaiou.entity.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-03-14:18
 */
public class putListAction extends ActionSupport{
    // 首先定义 List 变量
    private List<User> userList = new ArrayList<User>();
    // 同时实现 get 方法
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String execute() throws Exception {
        // 向 list 中设置值
        User user1 = new User();
        user1.setUsername("GJXAIOU");
        user1.setPassword("GJXAIOU");
        user1.setAddress("江苏");

        User user2 = new User();
        user2.setUsername("gjxaiou");
        user2.setPassword("gjxaiou");
        user2.setAddress("南京");

        userList.add(user1);
        userList.add(user2);
        return SUCCESS;
    }
}
