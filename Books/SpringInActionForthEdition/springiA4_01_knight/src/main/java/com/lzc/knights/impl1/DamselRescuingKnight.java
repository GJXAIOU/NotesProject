package com.lzc.knights.impl1;

import com.lzc.knights.Knight;
/**
 * Description: 与依赖对象高度耦合  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-13 10:45:00   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LZC         1.0         1.0 Version
 */
public class DamselRescuingKnight implements Knight {

	private RescueDamselQuest quest;

	public DamselRescuingKnight() {
		this.quest = new RescueDamselQuest();
	}

	public void embarkOnQuest() {
		quest.embark();
	}

}
