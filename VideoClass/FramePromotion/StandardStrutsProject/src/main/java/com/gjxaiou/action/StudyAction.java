package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GJXAIOU
 * @create 2019-09-29-19:21
 */
public class StudyAction extends ActionSupport {
    public String add(){
        System.out.println("add....");
        return "add";
    }

    public String update(){
        System.out.println("update....");
        return "update";
    }
}
