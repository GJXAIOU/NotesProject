package com.gjxaiou.action;

import com.gjxaiou.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GJXAIOU
 * @create 2019-09-30-14:31
 */
public class Form4Action extends ActionSupport {
    @Override
    public String execute() throws Exception{
        // 首先获取表单数据
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        // 封装到实体类对象里面
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        System.out.println(user);
        return NONE;
    }
}
