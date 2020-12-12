
 /**
 * FileName:     SpitterConfig.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package com.lzc.invoker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.lzc.invoker.service.SpitterService;

/**
 * 
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 16:24:53  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version
 */
@Configuration
public class SpitterConfig {

	@Bean
	public HttpInvokerProxyFactoryBean spitterService() {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl("http://localhost:8110/springiA4_15_httpinvoker/spitter.service");
		proxy.setServiceInterface(SpitterService.class);
		return proxy;
	}
	
}
