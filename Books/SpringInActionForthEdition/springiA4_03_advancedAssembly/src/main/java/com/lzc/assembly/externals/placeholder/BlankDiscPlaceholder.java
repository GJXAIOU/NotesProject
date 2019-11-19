package com.lzc.assembly.externals.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description: 引入外部资源文件，使用占位符表达式获取值  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-17 22:39:07   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-17   LZC         1.0         1.0 Version
 */
@Component
@PropertySource("classpath:externals/app.properties")//虽然该例中，扫描该类的配置文件已经引用了，该类可以无须引用，但正常情况下，哪个类需要引入外部属性就在哪个类中引用
public class BlankDiscPlaceholder {

//	@Value("${disc.title}")
	private final String title;
//	@Value("${disc.artist}")
	private final String artist;

//	public BlankDiscPlaceholder(String title, String artist) {
//		this.title = title;
//		this.artist = artist;
//	}
	
	public BlankDiscPlaceholder(@Value("${disc.title}")String title, @Value("${disc.artist}")String artist) {
		this.title = title;
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

}
