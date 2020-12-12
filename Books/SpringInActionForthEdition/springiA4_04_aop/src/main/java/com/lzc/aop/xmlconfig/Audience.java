
 /**
 * FileName:     Audience.java
 * Createdate:   2019-02-18 15:28:10   
 */

package com.lzc.aop.xmlconfig;

import org.aspectj.lang.ProceedingJoinPoint;

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

public class Audience {

	public void watch() {}
	
	public void silenceCellPhone() {
		System.out.println("手机调静音");
	}
	
	public void takeSeats() {
		System.out.println("就坐");
	}
	
	public void applause() {
		System.out.println("鼓掌！！！");
	}
	
	public void demandRefund() {
		System.out.println("要求退票退钱");
	}
	
	//和上面效果一致，不注释只是为了方便
	public void watchPerformance(ProceedingJoinPoint joinPoint) {
		try {
			System.out.println("手机调静音");
			System.out.println("就坐");
			joinPoint.proceed();
			System.out.println("鼓掌！！！");
		} catch (Throwable e) {
			System.out.println("要求退票退钱");
		}
	}
	
}
