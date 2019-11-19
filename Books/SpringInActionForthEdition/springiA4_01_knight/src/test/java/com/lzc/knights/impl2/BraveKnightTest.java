/**  
 * FileName:     BraveKnightTest.java
 * Createdate:   2019-02-13 11:19:12
 */  
package com.lzc.knights.impl2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.lzc.knights.Quest;

/**  
 * Description:   
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 11:19:12  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version  
 */

public class BraveKnightTest {

	@Test
	public void knightShouldEmbarkOnQuset() {
		//利用Mockito框架，返回一个自动生成的实现了Quest的实例
		Quest mockQuest = mock(Quest.class);
		BraveKnight knight = new BraveKnight(mockQuest);
		knight.embarkOnQuest();
		//Mockito框架验证Quest的mock实现只调用了1次embark()
		verify(mockQuest, times(1)).embark();
	}

}
