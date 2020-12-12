package com.gjxaiou.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author GJXAIOU
 * @create 2019-09-25-8:40
 */
@Component
// 表名该类为切面通知类
@Aspect
public class MyAdvice {
    @Before("com.gjxaiou.pointcut.AnnotationPointcut.demo()")
    public void myBefore(){
        System.out.println("前置通知");
    }
    @After("com.gjxaiou.pointcut.AnnotationPointcut.demo()")
    public void myAfter(){
        System.out.println("后置通知");
    }
    @AfterThrowing("com.gjxaiou.pointcut.AnnotationPointcut.demo()")
    public void myThrow(){
        System.out.println("异常通知");
    }
    @Around("com.gjxaiou.pointcut.AnnotationPointcut.demo()")
    public Object myAround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("前置环绕");
        Object proceedResult = p.proceed();
        System.out.println("后置环绕");
        return proceedResult;
    }
}
