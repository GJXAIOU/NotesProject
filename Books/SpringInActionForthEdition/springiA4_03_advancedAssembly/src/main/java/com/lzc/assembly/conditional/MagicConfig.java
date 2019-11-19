
 /**
 * FileName:     MagicConfig.java
 * Createdate:   2019-02-16 21:30:37   
 */

package com.lzc.assembly.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:30:37   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */

@Configuration
@PropertySource("conditional/magic.properties")
public class MagicConfig {

	/**
	* <p>描述：条件化地创建bean</p>
	* @return
	* @author LZC
	 */
	@Bean
	@Conditional(MagicExistsCondition.class)
	public MagicBean magicBean() {
		return new MagicBean();
	}
	
}
