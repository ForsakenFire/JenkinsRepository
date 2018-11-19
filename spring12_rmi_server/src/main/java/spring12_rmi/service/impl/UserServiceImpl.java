package spring12_rmi.service.impl;

import org.springframework.stereotype.Service;

import spring12_rmi.bean.User;
import spring12_rmi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User getUser(String name) {
		User user = new User();
		user.setAge(123);
		user.setName("rmi1:name="+name);
		user.setSex(1);
		return user;
	}

}
