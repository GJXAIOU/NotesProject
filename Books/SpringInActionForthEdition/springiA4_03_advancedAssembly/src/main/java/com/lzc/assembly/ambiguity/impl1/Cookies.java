
 /**
 * FileName:     Cookies.java
 * Createdate:   2019-02-16 21:50:43   
 */

package com.lzc.assembly.ambiguity.impl1;

import org.springframework.stereotype.Component;

import com.lzc.assembly.ambiguity.Dessert;

/**
 * Description: 小饼干  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:50:43   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */
@Component
public class Cookies implements Dessert {

	@Override
	public void desc() {
		System.out.println("cookies");
	}

}
