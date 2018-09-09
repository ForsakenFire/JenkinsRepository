package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import spring1.cook.Cook;

public class MainClass {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		context.start();
		Cook cook = context.getBean(Cook.class);
		cook.cook();
	}
}
