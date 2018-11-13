package spring11_cache.controller;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring11_cache.bean.User;
import spring11_cache.service.UserService;


@Controller
public class TestController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	UserService userService;
	
	/**
	 * 先保存后取值
	 * @param model
	 * @return
	 */
	@RequestMapping("/cacheTest")
	public String getUser(Model model){
		User user = new User();
		user.setAge(22);
		user.setName("11月13日/小蓝");
		user.setPhone("111");
		user.setSex(2);
		int savedId = userService.saveUser(user).getId();
		logger.info("user保存完成，Id为："+savedId+".开始取值");
		User user2 = userService.getUserById(savedId);
		logger.info(user2);
		model.addAttribute("userInfo",user2.toString());
		return "cacheTest";
	}
	
	
}
