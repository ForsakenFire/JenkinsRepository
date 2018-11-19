package spring12_rmi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import spring12_rmi.service.UserService;


@Configuration
public class RMIConfig {
	/**
	 * RMI使用
	 */
	//@Bean
	public RmiProxyFactoryBean rpfb() {
		RmiProxyFactoryBean rpfb = new RmiProxyFactoryBean();
		rpfb.setServiceInterface(UserService.class);
		rpfb.setServiceUrl("rmi://localhost/userServiceTest");
		return rpfb;
	}
	
	@Bean
	public HessianProxyFactoryBean userService() {
		HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
		proxy.setServiceInterface(UserService.class);
		proxy.setServiceUrl("http://localhost:8088/spring12_rmi_server/userService.service");
		return proxy;
	}
}
