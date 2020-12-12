package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author GJXAIOU
 * @create 2019-10-03-11:02
 */
public class UserAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // 1.获取 ActionContext 类的对象
        ActionContext actionContext = ActionContext.getContext();
        // 2.调用方法获取值栈对象
        ValueStack valueStack = actionContext.getValueStack();

        // 验证每个 Action 对象只有一个值栈对象
        ValueStack valueStack1 = actionContext.getValueStack();
        System.out.println(valueStack == valueStack1);

        return NONE;
    }
}
