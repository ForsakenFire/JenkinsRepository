package spring4.mainclass;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring4.aopclass.EatInterface;
import spring4.aopclass.WashInterface;

public class MainClass {
	/**
	 * 需要分别解除注释查看
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		context.start();
		EatInterface launchEat = context.getBean("launchImpl",EatInterface.class);
		EatInterface dinnerEat = context.getBean("dinnerImpl",EatInterface.class);
		EatInterface breakfastEat = context.getBean("breakfastImpl",EatInterface.class);
		//before切面
		//launchEat.eat();
		//launchEat.eat("123");
		//around切面
		dinnerEat.eat();
		//带参数传递
		//dinnerEat.eat("2");
		//DeclareParent为bean新增其他方法
		//WashInterface washImpl = (WashInterface)dinnerEat;
		//washImpl.wash();
		//XML进行配置，以及afterthrowing异常处理(包括xml和注解的)
		//breakfastEat.eat();
		//breakfastEat.eat("breakfast");
		context.close();
	}
}
