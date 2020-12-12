
 /**
 * FileName:     PerformanceConfig.java
 * Createdate:   2019-02-18 15:38:06   
 */

package com.lzc.aop.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lzc.aop.Performance;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 15:38:06   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@Configuration
@ComponentScan(basePackageClasses=Performance.class)
@EnableAspectJAutoProxy	//启用AspectJ自动代理，切面才不会只是普通的bean
public class PerformanceConfig {
	
}
