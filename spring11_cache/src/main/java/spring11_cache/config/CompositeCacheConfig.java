package spring11_cache.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import spring11_cache.bean.User;


/**
 * 可以同时启用多个cachemanager
 * shiyongCompositeCacheManager即可
 * @author TD
 *
 */
//@Configuration
public class CompositeCacheConfig {

	public CacheManager cacheManager(net.sf.ehcache.CacheManager cm,
			RedisTemplate<String, User> redisTemplate) {
		CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
		List<CacheManager> cacheManagerList = new ArrayList<>();
		cacheManagerList.add(new RedisCacheManager(redisTemplate));
		cacheManagerList.add(new EhCacheCacheManager(cm));
		compositeCacheManager.setCacheManagers(cacheManagerList);
		return compositeCacheManager;
	}
	
}
