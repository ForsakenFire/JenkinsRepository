package spring11_cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring11_cache.bean.User;
import spring11_cache.dao.StudentDao;
import spring11_cache.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private StudentDao dao;

	@Override
	public User saveUser(User user) {
		return dao.saveAndFlush(user);
	}


	@Override
	public User getUserById(int id) {
		return dao.getUserById(id);
	}


	@Override
	public void deleteUser(int id) {
		dao.delete(id);
	}

}
