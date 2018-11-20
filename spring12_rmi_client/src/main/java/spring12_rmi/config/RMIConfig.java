package spring12_rmi.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
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
		rpfb.setServiceUrl("rmi://localhost:8088/userServiceTest");
		return rpfb;
	}
	
	/**
	 * Hessian调用
	 * @return
	 */
	//@Bean
	public HessianProxyFactoryBean userService() {
		HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
		proxy.setServiceInterface(UserService.class);
		proxy.setServiceUrl("http://localhost:8088/spring12_rmi_server/userService.service");
		return proxy;
	}
	
	/**
	 * httpinvoker使用
	 * @return
	 */
	@Bean
	public HttpInvokerProxyFactoryBean userServiceProxy() {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl("http://localhost:8088/spring12_rmi_server/userService.service");
		proxy.setServiceInterface(UserService.class);
		return proxy;
	}
	
	/**
	 *  基于jax-ws未完成
	 * @return
	 * @throws MalformedURLException
	 */
	//@Bean
	public JaxWsPortProxyFactoryBean proxy() throws MalformedURLException {
		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
		proxy.setWsdlDocumentUrl(new URL("http://localhost:8088/jaxService/userService"));
		proxy.setServiceName("userService");
		proxy.setServiceInterface(UserService.class);
		return proxy;
	}
	
}
