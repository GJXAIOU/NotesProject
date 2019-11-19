
 /**
 * FileName:     SpitterConfig.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package com.lzc.invoker.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.lzc.invoker.service.SpitterService;
import com.lzc.invoker.service.SpitterServiceImpl;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 15:37:29  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version  
 */

@Configuration
public class SpitterConfig {

	//导出HttpInvoker服务（SpitterService）
	@Bean
	public HttpInvokerServiceExporter httpInvokerServiceExporter(
			SpitterService spitterService) {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(spitterService);
		exporter.setServiceInterface(SpitterService.class);
		return exporter;
	}
	
	/*
	 * 因为HttpInvokerServiceExporter是一个Spring MVC控制器，
	 * 我们需要建立一个URL处理器，映射HTTP URL到对应的服务上
	 */
	@Bean
	public HandlerMapping httpInvokerMapping() {
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.put("/spitter.service", "httpInvokerServiceExporter");//value与bean名一致
		handlerMapping.setMappings(mappings);
		return handlerMapping;
	}
	
	@Bean
	public SpitterService spitterService() {
		return new SpitterServiceImpl();
	}
}
