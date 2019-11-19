
 /**
 * FileName:     TrackCounterConfig.java
 * Createdate:   2019-02-18 16:43:11   
 */

package com.lzc.aop.annotation.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lzc.aop.annotation.BlankDisc;
import com.lzc.aop.annotation.aspect.TrackCounter;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 16:43:11   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@Configuration
@EnableAspectJAutoProxy	//开启AspectJ自动代理
public class TrackCounterConfig {

	@Bean
	public BlankDisc blankDisc() {
		BlankDisc blankDisc = new BlankDisc();
		blankDisc.setTitle("和自己对话");
		blankDisc.setArtist("林俊杰");
		List<String> tracks = new ArrayList<String>();
		tracks.add("不为谁而作的歌");
		tracks.add("too bad");
		tracks.add("关键词");
		tracks.add("只有要你的地方");
		//...
		blankDisc.setTracks(tracks);
		return blankDisc;
	}
	
	@Bean
	public TrackCounter trackCounter() {
		return new TrackCounter();
	}
}
