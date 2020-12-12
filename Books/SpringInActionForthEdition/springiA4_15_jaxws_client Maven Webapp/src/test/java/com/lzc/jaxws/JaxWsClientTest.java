
 /**
 * FileName:     JaxWsClientTest.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package com.lzc.jaxws;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.jaxws.bean.Spitter;
import com.lzc.jaxws.config.SpitterConfig;
import com.lzc.jaxws.service.SpitterService;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 17:24:08  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpitterConfig.class)
public class JaxWsClientTest {

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
