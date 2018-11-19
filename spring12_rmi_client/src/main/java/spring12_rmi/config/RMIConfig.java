package spring12_rmi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import spring12_rmi.service.UserService;


@Configuration
public class RMIConfig {
	@Bean
	public RmiProxyFactoryBean rpfb() {
		RmiProxyFactoryBean rpfb = new RmiProxyFactoryBean();
		rpfb.setServiceInterface(UserService.class);
		rpfb.setServiceUrl("rmi://localhost/userServiceTest");
		return rpfb;
	}
}
