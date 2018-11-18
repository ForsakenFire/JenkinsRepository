package controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring12_rmi_client.bean.User;
import spring12_rmi_client.service.UserService;



@Controller
public class TestController {
	
	/**
	 * 先保存后取值
	 * @param model
	 * @return
	 */
	@RequestMapping("/rmiTest")
	public String test(Model model){
		//User user1 = userService.getUser("test1");
		User user1 = new User();
		System.out.println(user1);
		model.addAttribute("userInfo",user1.toString());
		return "rmiTest1";
	}
	
	
}
