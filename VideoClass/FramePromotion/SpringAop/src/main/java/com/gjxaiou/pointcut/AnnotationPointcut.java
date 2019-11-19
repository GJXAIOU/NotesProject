package com.gjxaiou.pointcut;
import	java.awt.Point;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author GJXAIOU
 * @create 2019-09-25-8:31
 */
// 使用注解配置切点的方式
@Component("pointCut")
public class AnnotationPointcut {
    @Pointcut("execution(* com.gjxaiou.pointcut.AnnotationPointcut.demo())")
    public void demo(){
        System.out.println("demo");
    }
}
