
 /**
 * FileName:     SpELConfig.java
 * Createdate:   2019-02-17 18:40:48   
 */

package com.lzc.assembly.externals.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.lzc.assembly.externals.BlankDisc;

/**
 * Description: 使用占位符表达式的配置  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-17 18:40:48   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-17   LZC         1.0         1.0 Version  
 */
@Configuration
@ComponentScan	//扫描BlankDiscPlaceholder
@PropertySource("classpath:externals/app.properties")
public class PlaceholderConfig {
	
	@Value("${disc.title}")
	private String title;

	@Value("${disc.artist}")
	private String artist;

	@Bean
	public BlankDisc blankDisc() {
		return new BlankDisc(title, artist);
	}
	
}
