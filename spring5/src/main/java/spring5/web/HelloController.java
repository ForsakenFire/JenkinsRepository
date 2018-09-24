package spring5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("hello.do")
	public String hello(){
		System.out.println("HelloWorld!");
		return "Hello";
	}
}
