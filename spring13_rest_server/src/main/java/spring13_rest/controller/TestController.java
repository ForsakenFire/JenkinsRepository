package spring13_rest.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring13_rest.bean.User;



@RestController
public class TestController {
	
	
	/**
	 * 先保存后取值
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/restTest.do")
	public User test(Model model){
		User user1 = new User();
		user1.setName("resttest1");
		user1.setAge(321);
		return user1;
	}
}
