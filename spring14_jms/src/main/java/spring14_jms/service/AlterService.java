package spring14_jms.service;

import spring14_jms.bean.User;

public interface AlterService {
	void sendUser(User user); 
	
	User receiveUsr();
}
