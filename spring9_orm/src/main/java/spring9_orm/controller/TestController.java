package spring9_orm.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring9_orm.bean.User;
import spring9_orm.dao.HibernateDao;
import spring9_orm.dao.JPADao;
import spring9_orm.dao.SpringDataDao;

@Controller
public class TestController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private HibernateDao dao;
	@Autowired
	private JPADao dao2;
	@Autowired
	private SpringDataDao dao3;
	
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
	
	/**
	 * SpringDataJpa自动生成实现类
	 * @param model
	 * @return
	 */
	@RequestMapping("/springDataJpaTest")
	@Transactional
	public String getUser3(Model model){
		String name = dao3.getOne(1).getName();
		User user = new User();
		user.setAge(23);
		user.setName("小黄");
		user.setPhone("111222");
		user.setSex(1);
		long num = dao3.saveAndFlush(user).getId(); 
		System.out.println(num);
		model.addAttribute("userName",name);
		List<User> userList = dao3.getUserBySexOrderByIdDescPhoneAsc(Long.valueOf(2));
		for (User user2 : userList) {
			logger.info("遍历User：【"+user2+"】");
		}
		logger.info("遍历结束");
		return "hibernateTest";
	}
	
	
}
