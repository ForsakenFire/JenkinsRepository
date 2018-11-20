package spring12_rmi.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
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
	//@Bean
	public HessianServiceExporter hessianUserService(UserService userService) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(userService);
		exporter.setServiceInterface(UserService.class);
		return exporter;
	}
	
	/**
	 * Hessian调用所需的url映射bean。
	 * Spring的Httpinvoker也使用
	 * @return
	 */
	@Bean
	public HandlerMapping hessianMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		//mappings.setProperty("/userService.service", "hessianUserService");
		mappings.setProperty("/userService.service", "httpInvolkerService");
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
	
	/**
	 * Spring的httpinvoker导出为rmi服务。
	 * @return
	 */
	@Bean
	public HttpInvokerServiceExporter httpInvolkerService(UserService userService) {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(UserService.class);
		exporter.setService(userService);
		return exporter;
	}
	
	
	/**
	 * JAX-WS导出为webservice，无需额外配置（未完成）
	 * 会将所有带相关注释的都导出为webservice.
	 * 此时的jax-ws生命周期不由jax-ws管理，交由spring管理
	 * 如果生命周期由jax-ws管理，可以创建类继承SpringBeanAutowiringSupport，
	 * 在此类中可以注入spring所管理的bean
	 */
	//@Bean
	public SimpleJaxWsServiceExporter exporter() {
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		//通过设置baseaddress决定访问的前缀
		exporter.setBaseAddress("http://localhost:8088/jaxService/");
		return exporter;
	}
}
