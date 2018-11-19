package spring12_rmi.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring12_rmi.config.RootConfig;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		context.start();
		
	}
}
