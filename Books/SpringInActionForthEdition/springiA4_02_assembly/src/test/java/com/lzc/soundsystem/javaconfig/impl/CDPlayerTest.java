/**  
 * FileName:     CDPlayerTest.java
 * Createdate:   2019-02-13 14:24:27
 */  


package com.lzc.soundsystem.javaconfig.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.soundsystem.javaconfig.MediaPlayer;
import com.lzc.soundsystem.javaconfig.config.CDPlayerConfig;

/**  
 * Description: 使用javaconfig定义的bean测试注入  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 14:24:27  
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
	
	@Test
	public void play() {
		player.play();
	}

}
