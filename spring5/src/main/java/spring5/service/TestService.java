package spring5.service;

import spring5.entity.MyException;

public class TestService {
	public void exceptionTest() throws MyException{
		System.out.println("进入TestService.exceptionTest");
		//throw new MyException("这是service抛出的异常");
	}
}
