package spring12_rmi_server.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring12_rmi_server.config.RootConfig;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		context.start();
		
	}
}
