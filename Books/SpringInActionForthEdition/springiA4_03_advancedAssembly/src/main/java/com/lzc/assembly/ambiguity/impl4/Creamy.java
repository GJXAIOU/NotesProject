
 /**
 * FileName:     Creamy.java
 * Createdate:   2019-02-16 22:28:23   
 */

package com.lzc.assembly.ambiguity.impl4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Description: 自定义的@Qualifier  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 22:28:23   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR,
	ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Creamy {

}
