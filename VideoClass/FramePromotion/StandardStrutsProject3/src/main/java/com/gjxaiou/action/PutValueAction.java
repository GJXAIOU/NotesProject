package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author GJXAIOU
 * @create 2019-10-03-13:41
 */
public class PutValueAction extends ActionSupport {
//    @Override
//    public String execute() throws Exception {
//        ActionContext context = ActionContext.getContext();
//        ValueStack valueStack = context.getValueStack();
//
//        // 方式一：使用值栈对象中的 set 方法
//        valueStack.set("userName", "GJXAIOU");
//
//        // 方式二：调用 push 方法
//        valueStack.push("gjxaiou");
//
//        return SUCCESS;
//    }

    // 方式三：定义变量然后生成对应的 get 方法
    private String userName;

    public String getUserName() {
        return userName;
    }

    @Override
    public String execute() throws Exception {
        // 在执行的方法中面向变量设置值
        userName = "GJXaiou";
        return SUCCESS;
    }
}
