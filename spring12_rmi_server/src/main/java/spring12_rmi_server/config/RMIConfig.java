package spring12_rmi_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import spring12_rmi_server.service.UserService;

@Configuration
public class RMIConfig {
	@Bean
	public RmiServiceExporter rmiExplorer(UserService userService) {
		RmiServiceExporter serviceEplorer = new RmiServiceExporter();
		serviceEplorer.setService(userService);
		serviceEplorer.setServiceName("userServiceTest");
		serviceEplorer.setServiceInterface(UserService.class);
		System.out.println("service published");
		return serviceEplorer;
	}
}
