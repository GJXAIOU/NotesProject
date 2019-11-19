/**  
 * FileName:     CDPlayerTest.java
 * Createdate:   2019-02-13 14:03:35
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
import com.lzc.soundsystem.autoconfig.config.CDPlayerConfig;

/**  
 * Description: 加载javaconfig开启扫描，测试自动注入  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 14:03:35  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {

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
