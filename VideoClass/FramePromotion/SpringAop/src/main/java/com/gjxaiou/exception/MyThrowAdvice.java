package com.gjxaiou.exception;

/**
 * @author GJXAIOU
 * @create 2019-09-23-21:50
 */
public class MyThrowAdvice {
    public void MyException(Exception e){
        System.out.println("执行异常通知" + e.getMessage());
    }
}
