package spittr.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching // 启用缓存
public class CachingConfig2 {

	// 缓存管理器
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	//redis模板bean
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}
	
	//redis连接工厂
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("192.168.198.129");
		connectionFactory.setPassword("123456");
		return connectionFactory;
	}
	
	/**
	 * Spring3.1+内置的缓存管理器
	 * SimpleCacheManager
	 * NoOpCacheManager
	 * ConcurrentMapCacheManager
	 * CompositeCacheManager
	 * EhCacheCacheManager
	 * 
	 * Spring3.2+内置的缓存管理器
	 * RedisCacheManager（来自于Spring Data Redis项目）
	 * GemfireCacheManager（来自于Spring Data GemFire项目）
	 */
	
	/*
	 * Spring提供了四个注解来声明缓存规则
	 * 
	 * @Cacheable 	表明Spring在调用方法之前，首先应该在缓存中查找方法的返回值。
	 * 				如果这个值能够找到，就会返回缓存的值。否则的话，这个方法就会被调用，返回值会放到缓存之中
	 * @CachePut 	表明Spring应该将方法的返回值放到缓存中。在方法的调用前并不会检查缓存，方法始终都会被调用
	 * @CacheEvict 	表明Spring应该在缓存中清除一个或多个条目
	 * @Caching 	这是一个分组的注解，能够同时应用多个其他的缓存注解
	 */
	
	/*
	 * @Cacheable和@CachePut
	 * value 		String[] 	要使用的缓存名称
	 * condition 	String 		SpEL表达式，如果得到的值是false的话，不会将缓存应用到方法调用上
	 * key 			String 		SpEL表达式，用来计算自定义的缓存key
	 * unless 		String 		SpEL表达式，如果得到的值是true的话，返回值不会放到缓存之中
	 * 
	 * @CacheEvict
	 * value			String[]	要使用的缓存名称
	 * key 				String 		SpEL表达式，用来计算自定义的缓存key
	 * condition 		String		SpEL表达式，如果得到的值是false的话，缓存不会应用到方法调用上
	 * allEntries		boolean		如果为true的话，特定缓存的所有条目都会被移除掉
	 * beforeInvocation	boolean		如果为true的话，在方法调用之前移除条目。如果为 false （默认值）的话，
	 * 								在方法成功调用之后再移除条目
	 */
	
	/*
	 * Spring提供了多个用来定义缓存规则的SpEL扩展
	 * #root.args 			传递给缓存方法的参数，形式为数组
	 * #root.caches 		该方法执行时所对应的缓存，形式为数组
	 * #root.target 		目标对象
	 * #root.targetClass 	目标对象的类，是 #root.target.class 的简写形式
	 * #root.method 		缓存方法
	 * #root.methodName 	缓存方法的名字，是 #root.method.name 的简写形式
	 * #result 				方法调用的返回值（不能用在 @Cacheable 注解上）
	 * #Argument 			任意的方法参数名（如 #argName ）或参数索引（如 #a0 或 #p0 ）
	 */

}
