package com.gjxaiou.pointcut;
import	java.util.function.ToDoubleFunction;
import	java.lang.annotation.Annotation;

import lombok.*;

/**
 * @author GJXAIOU
 * @create 2019-09-23-21:10
 */
@Setter
@Getter

@ToString
public class AOPpointcut {
    public void AopPointcut(){
        System.out.println("这是切点");
    }
}
