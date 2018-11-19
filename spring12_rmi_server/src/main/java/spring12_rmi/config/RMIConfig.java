package spring12_rmi.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import spring12_rmi.service.UserService;

@Configuration
public class RMIConfig {
	/**
	 * 基本的rmi调用，基于二进制传输。只能在java环境中使用，限制较多。
	 * @param userService
	 * @return
	 */
	//@Bean
	public RmiServiceExporter rmiExplorer(UserService userService) {
		RmiServiceExporter serviceEplorer = new RmiServiceExporter();
		serviceEplorer.setService(userService);
		serviceEplorer.setServiceName("userServiceTest");
		serviceEplorer.setServiceInterface(UserService.class);
		System.out.println("service published");
		return serviceEplorer;
	}
	
	/**
	 * 基于hessian调用，通过二进制基于http协议传输。并且移植性强，支持非java环境。
	 * HessianServiceExporter实质是一个springmvc控制器。<br>
	 * 接收请求并转化为调用，从而完成hessian的导出（发布）<br>
	 * 无需对service进行命名，因为不是发布在RMI注册表中。
	 */
	@Bean
	public HessianServiceExporter hessianUserService(UserService userService) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(userService);
		exporter.setServiceInterface(UserService.class);
		return exporter;
	}
	
	/**
	 * Hessian调用所需的url映射bean。
	 * @return
	 */
	@Bean
	public HandlerMapping hessianMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.setProperty("/userService.service", "hessianUserService");
		mapping.setMappings(mappings);
		return mapping;
	}
	
	/**
	 * burlap，基于xml传输。方法已过时
	 * @param userService
	 * @return
	 */
	//@Bean
	public BurlapServiceExporter burlapUserService(UserService userService) {
		BurlapServiceExporter exporter = new BurlapServiceExporter();
		exporter.setService(userService);
		exporter.setServiceInterface(UserService.class);
		return exporter;
	}
}
