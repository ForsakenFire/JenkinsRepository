package spring12_rmi_server.service.impl;

import org.springframework.stereotype.Service;

import spring12_rmi_server.bean.User;
import spring12_rmi_server.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User getUser(String name) {
		User user = new User();
		user.setAge(1232);
		user.setName("RMI:name="+name);
		return user;
	}

}
