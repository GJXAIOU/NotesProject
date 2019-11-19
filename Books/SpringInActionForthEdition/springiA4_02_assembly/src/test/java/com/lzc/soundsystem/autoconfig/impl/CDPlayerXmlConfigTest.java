/**  
 * FileName:     CDPlayerXmlConfigTest.java
 * Createdate:   2019-02-13 14:09:56
 */  
package com.lzc.soundsystem.autoconfig.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.soundsystem.autoconfig.CompactDisc;
import com.lzc.soundsystem.autoconfig.MediaPlayer;

/**  
 * Description: 加载xmlconfig开启扫描，测试自动注入  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 14:09:56  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/autoconfig.xml")
public class CDPlayerXmlConfigTest {

	@Autowired
	private MediaPlayer player;
	
	@Autowired
	private CompactDisc cd;
	
	@Test
	public void cdShouldNotBeNull() {
		assertNotNull(cd);
	}
	
	@Test
	public void play() {
		player.play();
	}

}
