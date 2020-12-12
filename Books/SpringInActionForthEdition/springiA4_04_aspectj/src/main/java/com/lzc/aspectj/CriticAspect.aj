/**  
 * FileName:     CriticAspect.aj
 * Createdate:   2019-02-19 15:05:19
 */  


package com.lzc.aspectj;

/**  
 * Description:   
 * Copyright:   Copyright (c)2019 
 * Company:     rongji  
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-19 15:05:19  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-19   LiZC        1.0         1.0 Version  
 */  


public aspect CriticAspect {
	
	private CriticismEngine criticismEngine;
	
	public void setCriticismEngine(CriticismEngine criticismEngine) {
		this.criticismEngine = criticismEngine;
	}

	public CriticAspect() {}
	
	pointcut watch() : execution(* com.lzc.aspectj.Performance.perform(..));
	
	after() returning() : watch() {
		System.out.println(criticismEngine.getCriticism());
	}
}
