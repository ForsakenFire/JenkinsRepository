package spring12_rmi.config;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring12_rmi.bean.User;
import spring12_rmi.service.UserService;

@Component
@WebService(serviceName="userService")
public class UserServiceEndPointConfig {
	
	@Autowired
	UserService userService;
	
	@WebMethod
	public User getUser() {
		System.out.println("进入UserServiceEndPointConfig的getUser方法");
		User user1 = userService.getUser("jax-ws测试");
		return user1;
	}

}
