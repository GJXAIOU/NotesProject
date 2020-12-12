package com.gjxaiou.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GJXAIOU
 * @create 2019-09-30-11:02
 */
public class Form3Action extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest httpServletRequest;
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String execute() throws Exception {
        httpServletRequest.getParameter("");
        return NONE;
    }
}
