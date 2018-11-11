package spring10_nosql.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisSentinelConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import spring10_nosql.bean.User;

@Configuration
public class RedisConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 使用spring-data-redis，需要connectionfactory。
	 * @return
	 */
	@Bean
	public RedisConnectionFactory redisFactory(){
		RedisSentinelConfiguration config = new RedisSentinelConfiguration();
		config.setMaster(env.getProperty("redis.sentinels.mastername"));
		Set<RedisNode> sentinels = new HashSet<>();
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel1.address"), Integer.parseInt(env.getProperty("redis.sentinel1.port"))) );
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel2.address"), Integer.parseInt(env.getProperty("redis.sentinel2.port"))) );
		sentinels.add(new RedisNode(env.getProperty("redis.sentinel3.address"), Integer.parseInt(env.getProperty("redis.sentinel3.port"))) );
		config.setSentinels(sentinels);
		//config.setPassword(env.getProperty("redis.sentinels.password"));
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
	 * 使用非spring的主从+哨兵模式的redis配置
	 * @return
	 * @throws Exception
	 */
	@Bean
	public JedisSentinelPool jedisPool() throws Exception {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		Set<String> sentinels = new HashSet<>();
		sentinels.add(env.getProperty("redis.sentinel1"));
		sentinels.add(env.getProperty("redis.sentinel2"));
		sentinels.add(env.getProperty("redis.sentinel3"));
		config.setMaxIdle(10);
		config.setMaxTotal(100);
		config.setMaxWaitMillis(100);
		String password = env.getProperty("redis.password");
		String masterName = env.getProperty("redis.mastername");
		JedisSentinelPool pool = null;
		try {
			pool = new JedisSentinelPool(masterName, sentinels, config, password);
		} catch (Exception e) {
			throw new Exception("初始化redis连接池失败");
		}
		return pool;
	}
	
	
	
}
