package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.HttpParameters;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author GJXAIOU
 * @create 2019-09-30-9:09
 */
public class Form1Action extends ActionSupport {
    @Override
    public String execute() throws Exception{
        // 使用 Struts 中 ActionContext 对象接收表单的参数
        ActionContext actionContext = ActionContext.getContext();
        // 接收参数
        HttpParameters paramsMap = actionContext.getParameters();
        for(String key : paramsMap.keySet()){
            String value = paramsMap.get(key).getValue();
        }

        // 向 request 中存入数据 ： request.setAttribute(String name, Object value);
        actionContext.put("requestName","requestGJXAIOU");
        // 向 session 中存入数据： request.getSession().setAttribute(String name, Object value);
        actionContext.put("sessionName", "sessionGJXAIOU");
        // 向 application 中存入数据 this.getServletContext().setAttribute(String name, Object value);
        actionContext.getApplication().put("applicationName", "applicationGJXAIOU");


        return SUCCESS;
    }
}
