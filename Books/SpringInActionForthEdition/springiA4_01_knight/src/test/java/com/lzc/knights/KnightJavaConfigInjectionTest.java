/**  
 * FileName:     KnightJavaConfigInjectionTest.java
 * Createdate:   2019-02-13 13:24:16
 */  
package com.lzc.knights;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.knights.config.KnightConfig;

/**  
 * Description: 加载java config的注入测试  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 13:24:16  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=KnightConfig.class)
public class KnightJavaConfigInjectionTest {

	@Autowired
	Knight knight;
	
	@Test
	public void shouldInjectKnightWithSlayDragonQuest() {
		knight.embarkOnQuest();
	}

}
