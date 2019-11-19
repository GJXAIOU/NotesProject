package com.gjxaiou.action;

import com.gjxaiou.entity.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GJXAIOU
 * @create 2019-10-03-14:09
 */
public class putObjectAction extends ActionSupport {
    // 首先定义对象变量
    private User user = new User();
    // 然后生成对应的 get 方法
    public User getUser() {
        return user;
    }

    @Override
    public String execute() throws Exception {
        // 最后向值栈的 user 中放入数据
        user.setUsername("GJXAIOU");
        user.setPassword("gjxaiou");
        user.setAddress("南京");

        return SUCCESS;
    }
}
