package spring9_orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring9_orm.bean.User;
import spring9_orm.dao.HibernateDao;
import spring9_orm.dao.JPADao;

@Controller
public class TestController {

	@Autowired
	private HibernateDao dao;
	@Autowired
	private JPADao dao2;
	@RequestMapping("/hibernateTest")
	public String getUser(Model model){
		String name = dao.getName();
		User user = new User();
		user.setAge(22);
		user.setName("小张");
		user.setPhone("111");
		user.setSex(2);
		long num = dao.insertUser(user); 
		System.out.println(num);
		model.addAttribute("userName",name);
		return "hibernateTest";
	}
	
	@RequestMapping("/jpaTest")
	public String getUser2(Model model){
		String name = dao2.getName();
		User user = new User();
		user.setAge(23);
		user.setName("小绿");
		user.setPhone("111222");
		user.setSex(1);
		long num = dao2.insertUser(user); 
		System.out.println(num);
		model.addAttribute("userName",name);
		return "hibernateTest";
	}
}
