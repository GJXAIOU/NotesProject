
 /**
 * FileName:     InvokerClientTest.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package com.lzc.invoker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.invoker.bean.Spitter;
import com.lzc.invoker.config.SpitterConfig;
import com.lzc.invoker.service.SpitterService;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 16:28:39  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpitterConfig.class)
public class InvokerClientTest {
	
	@Autowired
	private SpitterService spitterService;
	
	@Test
	public void test() {
		Spitter spitter1 = spitterService.getById(1L);
		Spitter spitter2 = spitterService.getById(2L);
		assertEquals("张三", spitter1.getUsername());
		assertEquals("zhangsan233", spitter1.getPassword());
		assertEquals("李四", spitter2.getUsername());
		assertEquals("lisi55667788", spitter2.getPassword());
	}

}
