package spring4.mainclass;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring4.aopclass.EatInterface;

public class MainClass {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		context.start();
		EatInterface launchEat = context.getBean("launchImpl",EatInterface.class);
		//EatInterface dinnerEat = context.getBean("dinnerEat",EatInterface.class);
		launchEat.eat();
		launchEat.eat("123");
		context.close();
	}
}
