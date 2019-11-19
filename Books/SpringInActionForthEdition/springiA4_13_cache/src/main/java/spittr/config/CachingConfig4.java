package spittr.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching // 启用缓存
public class CachingConfig4 {

	@Bean
	public CacheManager cmCacheManager() {
		return new ConcurrentMapCacheManager();//基于内存
	}

}
