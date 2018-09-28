package spring5.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring5.entity.MyException;
import spring5.entity.User;
import spring5.service.TestService;

@Controller
public class HelloController {
	//jsp使用
	@RequestMapping("hello.do")
	public String hello(Model model,RedirectAttributes attribute) throws MyException{
		System.out.println("HelloWorld!");
		new TestService().exceptionTest();
		//1：重定向之前放入model，在重定向时会拼接到url上作为参数传递，不推荐
		model.addAttribute("test","你好啊的撒");
		//return "Hello";
		//2：通过flash方法在重定向后传值
		User user = new User();
		user.setCode("001");
		user.setName("flashTest");
		attribute.addFlashAttribute("user", user);
		return "redirect:/redirectTest.do";
	}
	
	
	@RequestMapping("/redirectTest.do")
	public String redirectTest(HttpServletRequest request){
		System.out.println(request.getRequestURI());
		String test = request.getParameter("test");
		request.setAttribute("test", test);
		User user = (User) request.getAttribute("user");
		request.setAttribute("user", user);
		System.out.println("进入redirectTest方法");
		return "RedirectTest";
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
