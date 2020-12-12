/**  
 * FileName:     KnightConfig.java
 * Createdate:   2019-02-13 11:57:35
 */  
package com.lzc.knights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzc.knights.Knight;
import com.lzc.knights.Quest;
import com.lzc.knights.impl2.BraveKnight;
import com.lzc.knights.impl2.SlayDragonQuest;

/**  
 * Description: JavaConfig，效果同knight.xml  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 11:57:35  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */
@Configuration
public class KnightConfig {
	
	@Bean
	public Knight knight() {
		return new BraveKnight(quest());
	}
	
	@Bean
	public Quest quest() {
		return new SlayDragonQuest(System.out);
	}

}
