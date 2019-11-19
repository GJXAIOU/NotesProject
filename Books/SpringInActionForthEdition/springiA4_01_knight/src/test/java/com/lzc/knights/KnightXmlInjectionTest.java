/**  
 * FileName:     KnightXmlInjectionTest.java
 * Createdate:   2019-02-13 13:30:37
 */  


package com.lzc.knights;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
 * Description:   
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 13:30:37  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:META-INF/spring/knight.xml")
@ContextConfiguration("classpath:META-INF/spring/minstrel.xml")
public class KnightXmlInjectionTest {

	@Autowired
	Knight knight;
	
	@Test
	public void shouldInjectKnightWithSlayDragonQuest() {
		knight.embarkOnQuest();
	}
}
