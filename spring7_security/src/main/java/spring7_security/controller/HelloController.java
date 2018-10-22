package spring7_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("hello")
	public String hello1(){
		System.out.println("This is Hello");
		return "hello";
	}
	
	@RequestMapping("hello2")
	public String hello2(){
		System.out.println("This is Hello2");
		return "hello2";
	}
	
	@RequestMapping("hello3")
	public String hello3(){
		System.out.println("this is Hello3");
		return "hello3";
	}
}
