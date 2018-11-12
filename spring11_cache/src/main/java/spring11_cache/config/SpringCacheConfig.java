package spring11_cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

/**
 * @author TD
 *
 */
@Configuration
public class SpringCacheConfig {

	/**
	 * 使用encache缓存,注意此处的cachemanager是ehcache的cachemanager，
	 * 做为spring的cachemanager的参数传入！
	 */
	//@Bean
	public EhCacheCacheManager cacheManager(CacheManager encache) {
		return new EhCacheCacheManager(encache);
	}
	
	/**
	 * factorybean在上下文中自动注入时会注入生成的bean，而不是它本身<br>
	 * Ehcache本身的cachemanager需要注入到spring的cachemanager中使用。
	 * 注意依赖来源
	 * 注意path：用/隔开
	 */
	//@Bean
	public EhCacheManagerFactoryBean encache() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		//声明配置文件
		factoryBean.setConfigLocation(new ClassPathResource("classpath:ehcache.xml"));
		return factoryBean;
	}
}
