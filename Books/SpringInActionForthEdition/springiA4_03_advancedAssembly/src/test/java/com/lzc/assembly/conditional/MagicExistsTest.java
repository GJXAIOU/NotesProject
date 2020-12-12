
 /**
 * FileName:     MagicExistsTest.java
 * Createdate:   2019-02-16 21:35:21   
 */

package com.lzc.assembly.conditional;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:35:21   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MagicConfig.class)
public class MagicExistsTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired(required=false)
	private MagicBean magicBean;

	@Test
	public void shouldNotNull() {
		assertTrue(context.containsBean("magicBean"));
		assertNotNull(magicBean);
	}

}
