package com.gjxaiou.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GJXAIOU
 * @create 2019-09-29-19:12
 */
public class BookAction extends ActionSupport {
    public String add(){
        System.out.println("add......");
        return NONE;
    }

    public String update(){
        System.out.println("update.......");
        return NONE;
    }
}
