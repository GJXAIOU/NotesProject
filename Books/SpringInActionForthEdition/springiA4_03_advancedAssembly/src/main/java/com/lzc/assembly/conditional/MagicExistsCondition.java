
 /**
 * FileName:     MagicExistsCondition.java
 * Createdate:   2019-02-16 21:27:09   
 */

package com.lzc.assembly.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:27:09   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */

public class MagicExistsCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获取环境
		Environment env = context.getEnvironment();
		//包含环境属性 magic 则返回true
		return env.containsProperty("magic");
	}

}
