
 /**
 * FileName:     TrackCounterTest.java
 * Createdate:   2019-02-18 16:49:17   
 */

package com.lzc.aop.annotation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.aop.annotation.aspect.TrackCounter;
import com.lzc.aop.annotation.config.TrackCounterConfig;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 16:49:17   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCounterTest {

	@Autowired
	private BlankDisc blankDisc;
	
	@Autowired
	private TrackCounter trackCounter;
	
	@Test
	public void test() {
		blankDisc.playTrack(1);
		blankDisc.playTrack(3);
		blankDisc.playTrack(3);
		blankDisc.playTrack(3);
		blankDisc.playTrack(4);
		blankDisc.playTrack(4);
		
		assertEquals(1, trackCounter.getPlayCount(1));
		assertEquals(0, trackCounter.getPlayCount(2));
		assertEquals(3, trackCounter.getPlayCount(3));
		assertEquals(2, trackCounter.getPlayCount(4));
	}

}
