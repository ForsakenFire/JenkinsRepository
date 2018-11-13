package spring11_cache.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import spring11_cache.bean.User;

/**
 * save方法在保存后放入缓存，每次保存均会刷新该值。
 * get方法直接从缓存中取，无需访问数据库.
 * key为ID！！！！
 * @author TD
 *
 */
public interface UserService {
	@Cacheable("user1")
	User getUserById(int id);
	
	/**
	 * 自定义的key：通过spel表达式自己定义key.
	 * key与上面的参数列表有关
	 * #result:返回值
	 * @param user
	 * @return
	 */
	@CachePut(value="user1",key="#result.id")
	User saveUser(User user);
}
