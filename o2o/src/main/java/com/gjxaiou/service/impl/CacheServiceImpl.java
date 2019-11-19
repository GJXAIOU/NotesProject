package com.gjxaiou.service.impl;


import com.gjxaiou.cache.JedisUtil;
import com.gjxaiou.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: 缓存服务接口实现
 *
 * @author tyronchen
 * @date 2019年1月26日
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private JedisUtil.Keys jedisKeys;


	@Override
	public void removeFromCache(String keyPrefix) {
		Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
		for (String key : keySet) {
			jedisKeys.del(key);
		}
	}

}
