package spring10_nosql.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import spring10_nosql.bean.User;

/**
 * 自动完成普通CRUD操作的实现补充<br>
 * 混合自定义的功能：创建一个中间接口，含有需要自定义实现的方法。
 * 然后创建MongoDaoImpl类实现该中间接口，同时MongoDao也需要实现该中间接口。
 * 通过名称将两者的对应方法进行关联，即可以自定义实现具体的操作。<br>
 * 可以通过EnableMongoRepositories的XXpostFix属性调整对应起来的后缀
 * 在xml中通过<mongo:repositories>的repositiryimpl-postfix调整
 * 
 * @author TD
 */
public interface MongoDao extends MongoRepository<User, Integer>{
	
	/**
	 * 自定义的方法，通过解析方法名补充实现
	 * @param name
	 * @return
	 */
	List<User> findByName(String name);
	
	/**
	 * 自定义查询条件,只接受json.
	 * ?0表示与参数的该索引处的值匹配
	 */
	//@Query("{'age':'21','phone':'?0'}")
	//List<User> getByPhone(String phone);
}
