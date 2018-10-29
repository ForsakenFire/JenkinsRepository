package spring9_orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring9_orm.bean.User;
import spring9_orm.dao.HibernateDao;

@Repository
public class HibernateDaoImpl implements HibernateDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	private Session getSession(){
		//该方法获取的session基于事务，需要配置事务管理器
		//普通session通过openSession获取，手动关闭即可
		return sessionFactory.getCurrentSession();
	}
	@Override
	@Transactional(readOnly=true)
	public String getName() {
		User user = (User) getSession().get(User.class,1);
		return user.getName();
	}

	@Override
	@Transactional
	public int insertUser(User user) {
		return (int) getSession().save(user);
	}


}
