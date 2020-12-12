package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author GJXAIOU
 * @create 2019-10-03-11:33
 */
public class ValueStackAction extends ActionSupport {
    @Override
    public String execute() throws Exception {

        ActionContext actionContext = ActionContext.getContext();
        ValueStack valueStack = actionContext.getValueStack();
        return SUCCESS;
    }
}
