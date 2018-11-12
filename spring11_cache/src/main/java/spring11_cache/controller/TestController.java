package spring11_cache.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring11_cache.bean.User;


@Controller
public class TestController {
	
	@RequestMapping("/cacheTest")
	public String getUser(Model model){
		User user = new User();
		user.setAge(22);
		user.setName("11月12日/小蓝");
		user.setPhone("111");
		user.setSex(2);
		model.addAttribute("userName","");
		return "mongoTest";
	}
	
	
}
