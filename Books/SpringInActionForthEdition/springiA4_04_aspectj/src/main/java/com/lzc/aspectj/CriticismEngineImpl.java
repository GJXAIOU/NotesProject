/**  
 * FileName:     CriticismEngineImpl.java
 * Createdate:   2019-02-19 15:12:53
 */  


package com.lzc.aspectj;

/**  
 * Description:   
 * Copyright:   Copyright (c)2019 
 * Company:     rongji  
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-19 15:12:53  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-19   LiZC        1.0         1.0 Version  
 */

public class CriticismEngineImpl implements CriticismEngine {

	private String[] criticismPool;
	
	public void setCriticismPool(String[] criticismPool) {
		this.criticismPool = criticismPool;
	}

	public CriticismEngineImpl() {}
	
	@Override
	public String getCriticism() {
		int i = (int) (Math.random() * criticismPool.length);
		return criticismPool[i];
	}

}
