package spring2.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring2.cook.Cook;
import spring2.javaconfig.JavaConfig;
import spring2.javaconfig.JavaConfig2;

public class MainClass {
	
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class,JavaConfig2.class);
		context.start();
		Cook cook1 = (Cook) context.getBean("cook1");
		System.out.println(cook1.cook());
		context.close();
	}
}
