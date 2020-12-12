
 /**
 * FileName:     PerformanceTest.java
 * Createdate:   2019-02-18 15:39:38   
 */

package com.lzc.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.aop.annotation.config.PerformanceConfig;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 15:39:38   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

public class PerformanceTest {

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=PerformanceConfig.class)
//	@ContextConfiguration("classpath:annotation/annotation.xml")
	public static class PerformanceConfigTest {
		@Autowired
		private Performance performance;
		
		@Test
		public void test() {
			performance.perform();
		}
		
	}
	
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:xmlconfig/performance.xml")
	public static class PerformanceXmlTest {
		@Autowired
		private Performance performance;
		
		@Test
		public void test() {
			performance.perform();
		}
		
	}

}
