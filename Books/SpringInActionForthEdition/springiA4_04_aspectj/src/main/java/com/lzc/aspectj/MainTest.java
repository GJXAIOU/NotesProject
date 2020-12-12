/**  
 * FileName:     MainTest.java
 * Createdate:   2019-02-19 15:45:15
 */  


package com.lzc.aspectj;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**  
 * Description:   
 * Copyright:   Copyright (c)2019 
 * Company:     rongji  
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-19 15:45:15  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-19   LiZC        1.0         1.0 Version  
 */

public class MainTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:performance.xml");
		Performance performance = (Performance) context.getBean("performance");
		performance.perform();
		context.close();
	}

}
