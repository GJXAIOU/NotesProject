
 /**
 * FileName:     TrackCounter.java
 * Createdate:   2019-02-18 16:34:20   
 */

package com.lzc.aop.annotation.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Description: 磁道计数器（处理通知中的参数）  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 16:34:20   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

@Aspect
public class TrackCounter {
	
	private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();
	
	//定义切点，通知playTrack方法
	@Pointcut("execution(** com.lzc.aop..BlankDisc.playTrack(int)) && args(trackNum)")
	public void trackPlayed(int trackNum) {}
	
	//在播放前，为该磁道计数
	@Before("trackPlayed(trackNum)")
	public void countTrack(int trackNum) {
		int currentCount = getPlayCount(trackNum);
		trackCounts.put(trackNum, currentCount + 1);
	}
	
	//获取某磁道的播放次数
	public int getPlayCount(int trackNum) {
		return trackCounts.containsKey(trackNum)?trackCounts.get(trackNum):0;
	}
	
}
