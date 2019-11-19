
 /**
 * FileName:     SingPerformance.java
 * Createdate:   2019-02-18 15:25:19   
 */

package com.lzc.aop;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 15:25:19   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@Component
public class SingPerformance implements Performance {

	@Override
	public void perform() {
		System.out.println("表演中...");
		//测试Encoreable时先注释，避免抛出异常影响测试
//		Random random = new Random(System.nanoTime());
//		if(random.nextInt(10) % 2 == 0) {	//[0,10)能被2整除就演砸
//			throw new RuntimeException("演砸了");
//		}
	}
}
