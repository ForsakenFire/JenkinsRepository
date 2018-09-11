package spring2.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring2.cook.Student;
@Configuration
public class JavaConfig2 {
	@Bean
	public Student student1(){
		Student student = new Student();
		student.setName("student1");
		return student;
	}
}
