
 /**
 * FileName:     Cake.java
 * Createdate:   2019-02-16 21:49:01   
 */

package com.lzc.assembly.ambiguity.impl1;

import org.springframework.stereotype.Component;

import com.lzc.assembly.ambiguity.Dessert;

/**
 * Description: 蛋糕  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:49:01   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */

@Component
public class Cake implements Dessert {

	@Override
	public void desc() {
		System.out.println("cake");
	}

}
