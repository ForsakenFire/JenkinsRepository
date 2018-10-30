package spring9_orm.dao;

import spring9_orm.bean.User;

public interface JPADao {
	String getName();
	int insertUser(User user);
}
