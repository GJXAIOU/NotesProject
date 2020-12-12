
 /**
 * FileName:     DessertTest.java
 * Createdate:   2019-02-16 22:02:43   
 */

package com.lzc.assembly.ambiguity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.assembly.ambiguity.config.Impl1Config;
import com.lzc.assembly.ambiguity.config.Impl2Config;
import com.lzc.assembly.ambiguity.config.Impl3Config;
import com.lzc.assembly.ambiguity.config.Impl4Config;
import com.lzc.assembly.ambiguity.impl4.Cold;
import com.lzc.assembly.ambiguity.impl4.Creamy;
import com.lzc.assembly.ambiguity.impl4.Fruity;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 22:02:43   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */

public class DessertTest {

	/**
	 * Description: 多个Dessert的实现类，将抛出NoUniqueBeanDefinitionException
	 * @author:     LZC  
	 * @date:   	2019-02-16 22:03:58   
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=Impl1Config.class)
	public static class Impl1Test {
		@Autowired
		private Dessert dessert;
		
		@Test
		public void test() {
			dessert.desc();
		}
	}
	
	/**
	 * Description: @Primary Bean 的测试
	 * @author:     LZC  
	 * @date:   	2019-02-16 22:03:58   
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=Impl2Config.class)
	public static class Impl2Test {
		@Autowired
		private Dessert dessert;
		
		@Test
		public void testPrimaryBean() {
			/*
			 * 当只有一个@Primary修饰Dessert的实现类，则成功
			 * 多个则抛出将抛出NoUniqueBeanDefinitionException
			 */
			dessert.desc();
		}
	}
	
	/**
	 * Description: @Qualifier Bean 的测试
	 * @author:     LZC  
	 * @date:   	2019-02-16 22:03:58   
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=Impl3Config.class)
	public static class Impl3Test {
		@Autowired
		@Qualifier("cake")
//		@Qualifier("cold")
		private Dessert dessert;
		
		@Test
		public void testQualifierBean() {
			/*
			 * 当@Qualifier的限定符在Dessert的实现类中唯一时，则成功
			 * 多个则抛出将抛出NoUniqueBeanDefinitionException
			 */
			dessert.desc();
		}
	}
	
	/**
	 * Description: 自定义@Qualifier Bean 的测试
	 * @author:     LZC  
	 * @date:   	2019-02-16 22:03:58   
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=Impl4Config.class)
	public static class Impl4Test {
		@Autowired
		@Cold
		@Creamy
		private Dessert dessert1;
		@Autowired
		@Cold
		@Fruity
		private Dessert dessert2;
		
		@Test
		public void testQualifierBean() {
			dessert1.desc();
			dessert2.desc();
		}
	}
}
