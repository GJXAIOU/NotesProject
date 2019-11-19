
 /**
 * FileName:     Impl1Config.java
 * Createdate:   2019-02-16 21:58:57   
 */

package com.lzc.assembly.ambiguity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 扫描impl4  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-16 21:58:57   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-16   LZC         1.0         1.0 Version  
 */
@Configuration
@ComponentScan(basePackages="com.lzc.assembly.ambiguity.impl4")
public class Impl4Config {

}
