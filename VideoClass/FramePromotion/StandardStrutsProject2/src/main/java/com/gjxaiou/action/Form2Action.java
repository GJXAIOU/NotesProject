package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author GJXAIOU
 * @create 2019-09-30-9:32
 */
public class Form2Action extends ActionSupport {
    /**
     *  在 action 中操作域对象
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        // request 域
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("req", "reqValue");

        // session 域
        HttpSession session = request.getSession();
        session.setAttribute("sess", "sessValue");

        // ServletContext 域
        ServletContext servletContext = ServletActionContext.getServletContext();
        session.setAttribute("contextname", "contextValue");
        return NONE;
    }

//    @Override
//    public String execute() throws Exception {
//        // 使用 ServletActionContext 类获取 request 对象
//        HttpServletRequest request = ServletActionContext.getRequest();
//
//        // 调用 request 中方法获取结果
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String address = request.getParameter("address");
//        System.out.println(username + ":" + password + ":" + address);
//
//        return NONE;
//    }

}
