package spring3.mainclass;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import spring3.student.Student;

public class MainClass {
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		context.start();
		Student student = context.getBean(Student.class);
		System.out.println(student.getName());
		context.close();
	}
}
