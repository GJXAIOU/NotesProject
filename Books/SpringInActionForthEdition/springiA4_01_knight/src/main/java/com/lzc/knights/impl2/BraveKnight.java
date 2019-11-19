package com.lzc.knights.impl2;

import com.lzc.knights.Knight;
import com.lzc.knights.Quest;

/**
 * Description: 依赖注入 —— 构造器注入  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-13 10:47:29   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LZC         1.0         1.0 Version
 */
public class BraveKnight implements Knight {

	private Quest quest;

	public BraveKnight(Quest quest) {
		this.quest = quest;
	}

	public void embarkOnQuest() {
		quest.embark();
	}

}
