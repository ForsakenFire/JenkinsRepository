package spring3.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Student {
	private String name;
	private int age;
	private Environment env;
	
	public String getName() {
		return name;
	}
	@Value("${student.testname}")
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Environment getEnv() {
		return env;
	}
	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	
	
}
