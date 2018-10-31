package spring9_orm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import spring9_orm.bean.User;

/**
 * SpringData:主需要关注接口的定义，无需关注具体的实现
 * @author TD<hr>
 * JPA可以通过接口中定义的方法签名自动分析可能的操作
 * 方法名由查询动词、主题和断言组成。<hr>
 * 动词：<br>
 * find/get/read:查询并返回实体<br>
 * count:获取匹配的记录条数<hr>
 * 主题：一般不影响查询结果，但是以Distinct开头会返回无重复的结果
 * <hr>
 * 断言：BYNameOrCode(String name,String code)。
 * 断言可以使用比较操作：例如 isnull greaterThan  isLike等。<br>
 * 可以使用IgnoresCase忽略大小写，可以最后添加OrderBy指定排序
 * 
 * 
 *
 */
public interface SpringDataDao extends JpaRepository<User, Integer>{
	/**
	 *通过phone获取实体
	 */
	User getUserByPhone(String phone);
	
	List<User> getUserBySexOrderByIdDescPhoneAsc(Long sex);
	
	/**
	 * 通过query注解自定义sql
	 * 通过modifying注解声明该sql会update<br>
	 * 自定义其他功能：创建SpringDataDaoImpl类，jpa会扫描并且将该类的同名方法与接口内的方法合并（无需实现接口）
	 * 在impl方法内通过EntityManager完成操作也可以实现。
	 * 可能需要使得接口类和impl类实现相同方法
	 * @param sex
	 * @return
	 */
	@Query(value="select name from User where id = ?1",nativeQuery=true)
	List<String> findUserABC(Long sex);
	
}
