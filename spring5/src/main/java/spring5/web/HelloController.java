package spring5.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring5.entity.User;

@Controller
public class HelloController {
	//jsp使用
	@RequestMapping("hello.do")
	public String hello(){
		System.out.println("HelloWorld!");
		return "Hello";
	}
	
	/**
	 * thiles使用
	 * @return
	 */
	@RequestMapping("tiles.do")
	public String tiles(){
		return "base";
	}
	
	//thymeleaf
	@RequestMapping("thymeleaf.do")
	public String thymeleaf(Model model){
		User user = new User();
		user.setCode("testcode");
		user.setName("testName");
		model.addAttribute(user);
		return "ThymeleafTest";
	}
	
	//thymeleaf表单绑定
	@RequestMapping("thymeleaftest.do")
	public String thymeleafForm(User user,Model model){
		System.out.println("表单绑定。。。");
		user.setCode("testCode2");
		user.setName("testName2");
		model.addAttribute("user",user);
		return "ThymeleafTest";
	}
}
