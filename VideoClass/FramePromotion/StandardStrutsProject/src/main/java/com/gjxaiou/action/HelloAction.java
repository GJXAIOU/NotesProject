package com.gjxaiou.action;

/**
 * @author GJXAIOU
 * @create 2019-09-29-15:53
 */
public class HelloAction {
    /**
     * (1) 之前项目中，每次访问 servlet 的时候，都会执行 service 方法；
     *（2）在 Struts2 中，每次访问 action 都会默认执行 execute 方法；
     */
    public String execute(){
        return "OK";
    }
}
