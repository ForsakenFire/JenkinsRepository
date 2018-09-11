package spring2.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring2.cook.Cook;
import spring2.cook.Student;

/**
 * 通过java配置类实现spring的配置
 * @author TD
 *
 */
@Configuration
public class JavaConfig {
	
	@Bean
	public Cook cook1(Student student2){
		Cook cook = new Cook();
		cook.setName("cook1");
		cook.setStudent(student2);
		return cook;
	}
	
	
}
