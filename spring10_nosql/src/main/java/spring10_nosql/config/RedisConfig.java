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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Configuration
public class RedisConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 使用主从+哨兵模式的redis配置
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
	
	@Bean()
	@Scope("prototype")
	public Jedis jedis(JedisSentinelPool jedisPool) {
		return jedisPool.getResource();
	} 
	
}
