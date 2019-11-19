package com.gjxaiou.service;

/**
 * @Description: 缓存服务接口
 */
public interface CacheService {

	/**
	 * 依据key前缀匹配原则删除缓存数据(key - value 值),如以 shopCategory 开头的 key- value 都会被清空；
	 * 
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
