package spring11_cache.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import spring11_cache.bean.User;

/**
 * @author TD
 *
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis2.properties")
public class RedisCacheConfig {
	@Autowired
	Environment env;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisSentinelConfiguration config = new RedisSentinelConfiguration();
		config.setMaster(env.getProperty("redis.sentinels.mastername"));
		Set<RedisNode> sentinels = new HashSet<>();
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel1.address"), Integer.parseInt(env.getProperty("redis.sentinel1.port"))) );
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel2.address"), Integer.parseInt(env.getProperty("redis.sentinel2.port"))) );
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel3.address"), Integer.parseInt(env.getProperty("redis.sentinel3.port"))) );
		config.setSentinels(sentinels);
		JedisConnectionFactory redisFactory = new JedisConnectionFactory(config);
		redisFactory.setPassword(env.getProperty("redis.sentinels.password"));
		return redisFactory;
	}
	
	
	@Bean
	public RedisTemplate<String , User> redisTemplate(RedisConnectionFactory redisFactory){
		RedisTemplate<String, User> template = new RedisTemplate<>();
		template.setConnectionFactory(redisFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
		return template;
	}
	
	/**
	 * 构造redis的cachemanager需要一个redistemplate作为构造参数
	 * spring直接支持，无需redis的cachemanager
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, User> redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}
	
}
