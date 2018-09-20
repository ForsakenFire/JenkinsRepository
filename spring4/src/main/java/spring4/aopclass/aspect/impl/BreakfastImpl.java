package spring4.aopclass.aspect.impl;

import spring4.aopclass.EatInterface;

public class BreakfastImpl implements EatInterface{

	@Override
	public void eat() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("无参的breakfast的eat方法");
		System.out.println("breakfast的eat抛出异常");
		throw new Exception("breakfast的eat抛出的异常");
	}

	@Override
	public String eat(String name) {
		// TODO Auto-generated method stub
		System.out.println("有参的breakfast的eat方法：name="+name);
		return "有参的breakfast的eat方法：name="+name;
	}

}
