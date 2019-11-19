
 /**
 * FileName:     Audience.java
 * Createdate:   2019-02-18 15:28:10   
 */

package com.lzc.aop.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 15:28:10   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@Aspect	//定义切面
@Component	//同时声明bean
public class Audience {

	//定义切点
	@Pointcut("execution(** com.lzc.aop.Performance.perform(..))")
	public void watch() {}
	
	//前置通知
	@Before("watch()")
	public void silenceCellPhone() {
		System.out.println("手机调静音");
	}
	
	//前置通知
	@Before("watch()")
	public void takeSeats() {
		System.out.println("就坐");
	}
	
	//后置返回通知
	@AfterReturning("watch()")
	public void applause() {
		System.out.println("鼓掌！！！");
	}
	
	//后置异常通知
	@AfterThrowing("watch()")
	public void demandRefund() {
		System.out.println("要求退票退钱");
	}
	
//	@After 后置通知，等同于@AfterReturning + @AfterThrowing
	
	//环绕通知，等同于上面四个通知。要使用时先注释上面四个通知
//	@Around("watch()")
//	public void watchPerformance(ProceedingJoinPoint joinPoint) {
//		try {
//			System.out.println("手机调静音");
//			System.out.println("就坐");
//			joinPoint.proceed();
//			System.out.println("鼓掌！！！");
//		} catch (Throwable e) {
//			System.out.println("要求退票退钱");
//		}
//	}
	
	//环绕通知，与上面四个通知共用
	@Around("watch()")
	public void watchPerformance(ProceedingJoinPoint joinPoint) {
		try {
			System.out.println("走向观众席");//先于@Before
			joinPoint.proceed();
			System.out.println("欢呼！！！");//先于@After
		} catch (Throwable e) {
			System.out.println("倒喝彩");//先于@AfterThrowing
			throw new RuntimeException(e);//如果不抛出，则不会执行@AfterThrowing
		}
	}
}
