package spring9_orm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring9_orm.bean.User;
import spring9_orm.dao.JPADao;

@Repository
@Transactional()
public class JPADaoImpl implements JPADao{
	/**
		@PersistenceUnit
		private EntityManagerFactory emf;
	emf创建的entityManager不是线程安全的，不适合注入到共享的单例bean中
	所以使用@PersistenceContext
	@PersistenceContext：为Repository提供了一个代理，实际上使用的是与当前事务关联的em
	 */
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private EntityManagerFactory factory;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public String getName(){
		//emf.createEntityManager().find(User.class,1);
		User user = em.find(User.class,6);
		return user.getName();
	}

	/**
	 * 使用@PersistenceContext注入的em无法持久化，需要手动管理事务？？？
	 */
	@Override
	public int insertUser(User user) {
		//emf.createEntityManager().persist(user);
		//em.persist(user);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		logger.info("持久化完成");
		return 0;
	}
}
