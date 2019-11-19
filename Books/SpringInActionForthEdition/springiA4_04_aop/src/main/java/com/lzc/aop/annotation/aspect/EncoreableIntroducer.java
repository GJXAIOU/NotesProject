
 /**
 * FileName:     EncoreableIntroducer.java
 * Createdate:   2019-02-18 17:07:01   
 */

package com.lzc.aop.annotation.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.lzc.aop.annotation.DefaultEncoreable;
import com.lzc.aop.annotation.Encoreable;

/**
 * Description: 通过注解引入新功能  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 17:07:01   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */
@Aspect
public class EncoreableIntroducer {

	/**
	 * @DeclareParents 所标注的静态属性指明了要引入的接口
	 * 
	 * 将接口Encoreable引入到Performance的bean中
	 * 1.value指定哪种类型的bean要引入改接口，本例中即实现了Performance的类型（“+”表示Performance的所有子类，而不是其本身）
	 * 2.defaultImpl表示为被引入接口的实现类
	 */
	@DeclareParents(value="com.lzc.aop.Performance+",
			defaultImpl=DefaultEncoreable.class)
	public static Encoreable encoreable;
}
