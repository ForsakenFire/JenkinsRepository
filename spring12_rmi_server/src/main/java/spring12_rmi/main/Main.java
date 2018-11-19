package spring12_rmi.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring12_rmi.config.RootConfig;

/**
 * Hessian调用需要通过springmvc的前端控制器
 * @author dongtian
 *
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		context.start();
		
	}
}
