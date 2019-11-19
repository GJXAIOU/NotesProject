
 /**
 * FileName:     EncoreableConfig.java
 * Createdate:   2019-02-18 17:17:33   
 */

package com.lzc.aop.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lzc.aop.Performance;
import com.lzc.aop.SingPerformance;
import com.lzc.aop.annotation.aspect.EncoreableIntroducer;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 17:17:33   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */
@Configuration
@EnableAspectJAutoProxy
public class EncoreableConfig {

	@Bean
	public Performance performance() {
		return new SingPerformance();
	}
	
	@Bean
	public EncoreableIntroducer encoreableIntroducer() {
		return new EncoreableIntroducer();
	}
}
